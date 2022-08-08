package com.example.quizmaster_backend.service;

import com.example.quizmaster_backend.exception.BadOperationException;
import com.example.quizmaster_backend.exception.DataNotFoundException;
import com.example.quizmaster_backend.model.AnswerLetter;
import com.example.quizmaster_backend.model.Question;
import com.example.quizmaster_backend.model.dto.response.PossibleAnswerDto;
import com.example.quizmaster_backend.model.dto.response.QuestionPlayFormatDto;
import com.example.quizmaster_backend.repository.PredefinedQuizQuestionsRepository;
import com.example.quizmaster_backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    /*======================================*
     * FIELDS
     *======================================*/

    private final QuestionRepository questionRepository;
    private final PredefinedQuizQuestionsRepository predefinedQuizQuestionsRepository;
    private final MessageSource messageSource;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    /**
     * Creates a service for handling the business logic for questions like creating new questions or deleting them.
     *
     * @param questionRepository the data repository for questions
     * @param predefinedQuizQuestionsRepository the data repository for quiz - question relations
     * @param messageSource a message source for localized strings from resources
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository, PredefinedQuizQuestionsRepository predefinedQuizQuestionsRepository, MessageSource messageSource) {
        this.questionRepository = questionRepository;
        this.predefinedQuizQuestionsRepository = predefinedQuizQuestionsRepository;
        this.messageSource = messageSource;
    }

    /*======================================*
     * CREATE
     *======================================*/

    /**
     * Adds a question with the given data to the database.
     *
     * @param questionText the text of the question
     * @param correctAnswer the text of the correct answer
     * @param wrongAnswers the texts of the wrong answers (validity already checked!)
     *
     * @return the created question
     */
    public Question addQuestion(String questionText, String correctAnswer, List<String> wrongAnswers) {
        Question question = new Question(questionText, correctAnswer, wrongAnswers.get(0), wrongAnswers.get(1), wrongAnswers.get(2));
        return this.questionRepository.save(question);
    }

    /*======================================*
     * READ
     *======================================*/

    /**
     * Returns the count of all questions inside the database.
     *
     * @return the count of all questions inside the database
     */
    public long getCountOfAllQuestions() {
        return questionRepository.count();
    }

    /**
     * Returns all questions of the database.
     * <br />
     * Does not return QuestionDto objects but Question objects since this request is not supposed to be
     * used for playing a quiz but for managing questions.
     *
     * @return all questions of the database
     */
    public Iterable<Question> getAllQuestionsInRawFormat() {
        return questionRepository.findAll();
    }


    public Question getRawQuestionById(Long id, Locale locale) {

        // get question
        Optional<Question> question = questionRepository.findById(id);

        // throw exception if no question with the specified id was found
        if (question.isEmpty()) {
            throw new DataNotFoundException(messageSource.getMessage("QuestionService.NotFound", null, locale));
        }

        // return the raw question
        return question.get();
    }

    /**
     * Returns the question with the given ID in play format. This format is supposed to be used when  playing a quiz
     * and contains, as an example, answer letters for an easier use in the frontend.
     * <br />
     * If the question does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param id the id of the requested question
     * @param locale the locale of the user
     *
     * @return the requested question in play format if the request is valid
     */
    public QuestionPlayFormatDto getPlayFormatQuestionById(Long id, Locale locale) {

        // get question
        Optional<Question> question = questionRepository.findById(id);

        // throw exception if no question with the specified id was found
        if (question.isEmpty()) {
            throw new DataNotFoundException(messageSource.getMessage("QuestionService.NotFound", null, locale));
        }

        // create and return dto for user
        String questionText = question.get().getQuestionText();
        PossibleAnswerDto[] possibleAnswers = createPossibleAnswers(question.get());
        AnswerLetter correctAnswer = getCorrectAnswer(possibleAnswers, question.get().getCorrectAnswer());

        return new QuestionPlayFormatDto(id, questionText, possibleAnswers, correctAnswer);
    }

    /**
     * Creates an array with four [answer letter, answer text] elements for each AnswerLetter A, B, C, D and each
     * possible answer of the given question. Thus, the returned array can directly be used by the frontend for
     * populating the answers view without needing to shuffle the answers etc.
     *
     * @param question the question for which the answer array should be created
     * @return the array as described above
     */
    private PossibleAnswerDto[] createPossibleAnswers(Question question) {

        // array to be returned
        PossibleAnswerDto[] possibleAnswers = new PossibleAnswerDto[4];

        // create list of 0, 1, 2, 3 and shuffle it
        List<Integer> shuffledIndices = new ArrayList<>(4);
        shuffledIndices.add(0);
        shuffledIndices.add(1);
        shuffledIndices.add(2);
        shuffledIndices.add(3);
        Collections.shuffle(shuffledIndices);

        // shuffle answers with help of the shuffled list
        for (int i = 0; i < 4; i++) {

            // get A, B, C, D in correct order
            AnswerLetter answerLetter = AnswerLetter.values()[i];

            // get random answer
            int randomIndex = shuffledIndices.remove(0);
            String answer = "";
            switch (randomIndex) {
                case 0:
                    answer = question.getCorrectAnswer();
                    break;
                case 1:
                    answer = question.getWrongAnswer1();
                    break;
                case 2:
                    answer = question.getWrongAnswer2();
                    break;
                case 3:
                    answer = question.getWrongAnswer3();
                    break;
            }

            // create array with random answer while the answer letters remain in the order A, B, C, D
            possibleAnswers[i] = new PossibleAnswerDto(answerLetter, answer);
        }

        // return filled array
        return possibleAnswers;
    }

    /**
     * Returns the answer letter of the correct answer like it is defined in the given array of all possible answers.
     *
     * @param possibleAnswers the possible answers A, B, C, D including an random ordered answer text
     * @param correctAnswer the correct answer as text
     * @return the answer letter of the correct answer inside the given array
     */
    private AnswerLetter getCorrectAnswer(PossibleAnswerDto[] possibleAnswers, String correctAnswer) {
        for (PossibleAnswerDto possibleAnswer : possibleAnswers) {
            if (possibleAnswer.getAnswerText().equals(correctAnswer)) {
                return possibleAnswer.getAnswerLetter();
            }
        }
        return null; // needed for compiler, never reached due to initialization process of possibleAnswers[]
    }

    /*======================================*
     * UPDATE
     *======================================*/

    /**
     * Updates the question given by its ID with the given data.
     * <br />
     * If the question does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param questionId the ID of the question to update
     * @param questionText the (updated) text of the question
     * @param correctAnswer the (updated) text of the correct answer
     * @param wrongAnswers the (updated) texts of the wrong answers (validity already checked!)
     * @param locale the locale of the user
     */
    public void updateQuestion(Long questionId, String questionText, String correctAnswer, List<String> wrongAnswers, Locale locale) {

        // get the requested question from the database if existing
        // => if not existing throw an exception
        Optional<Question> question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuestionService.NotFound", null, locale));
        }

        // update question and save the changes into the database
        Question updatedQuestion = question.get();

        updatedQuestion.setQuestionText(questionText);
        updatedQuestion.setCorrectAnswer(correctAnswer);
        updatedQuestion.setWrongAnswer1(wrongAnswers.get(0));
        updatedQuestion.setWrongAnswer2(wrongAnswers.get(1));
        updatedQuestion.setWrongAnswer3(wrongAnswers.get(2));

        this.questionRepository.save(updatedQuestion);
    }

    /*======================================*
     * DELETE
     *======================================*/

    /**
     * Deletes the question given by its ID from the database.
     * <br />
     * If the question does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param questionId the ID of the question to delete
     * @param locale the locale of the user
     */
    public void deleteQuestion(Long questionId, Locale locale) {

        // check if the question to be deleted is actual existing
        // => if not existing throw an exception
        boolean questionExists = questionRepository.existsById(questionId);

        if (!questionExists) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuestionService.NotFound", null, locale));
        }

        // check if the question is not contained in a predefined quiz
        // => if contained in a predefined quiz throw an exception
        boolean questionIsUsed = predefinedQuizQuestionsRepository.existsByQuestionId(questionId);

        if (questionIsUsed) {
            throw new BadOperationException(this.messageSource.getMessage("QuestionService.QuestionToBeDeletedInUse", null, locale));
        }

        // delete question
        questionRepository.deleteById(questionId);
    }
}
