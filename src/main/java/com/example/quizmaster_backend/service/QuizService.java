package com.example.quizmaster_backend.service;

import com.example.quizmaster_backend.exception.DataAlreadyExistingException;
import com.example.quizmaster_backend.exception.DataNotFoundException;
import com.example.quizmaster_backend.model.PredefinedQuiz;
import com.example.quizmaster_backend.model.PredefinedQuizQuestion;
import com.example.quizmaster_backend.model.Question;
import com.example.quizmaster_backend.model.dto.response.PredefinedQuizDto;
import com.example.quizmaster_backend.repository.PredefinedQuizQuestionsRepository;
import com.example.quizmaster_backend.repository.PredefinedQuizRepository;
import com.example.quizmaster_backend.repository.QuestionRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    private final MessageSource messageSource;
    private final PredefinedQuizRepository predefinedQuizRepository;
    private final PredefinedQuizQuestionsRepository predefinedQuizQuestionsRepository;
    private final QuestionRepository questionRepository;

    public QuizService(
            MessageSource messageSource,
            PredefinedQuizRepository predefinedQuizRepository,
            PredefinedQuizQuestionsRepository predefinedQuizQuestionsRepository,
            QuestionRepository questionRepository) {

        this.messageSource = messageSource;
        this.predefinedQuizRepository = predefinedQuizRepository;
        this.predefinedQuizQuestionsRepository = predefinedQuizQuestionsRepository;
        this.questionRepository = questionRepository;
    }

    /*======================================*
     * CREATE
     *======================================*/

    /**
     * Adds a question with the given data to the database.
     *
     * @param quizName the name of the quiz
     * @param locale the locale of the user
     *
     * @return the created predefined quiz
     */
    public PredefinedQuiz createPredefinedQuiz(String quizName, Locale locale) {

        // throw exception if a predefined quiz with the given name is already existing
        Optional<PredefinedQuiz> existingQuiz = predefinedQuizRepository.findByQuizName(quizName);
        if(existingQuiz.isPresent()) {
            throw new DataAlreadyExistingException(this.messageSource.getMessage("QuizService.QuizNameAlreadyExisting", null, locale));
        }

        // create, save and return new predefined quiz
        PredefinedQuiz quiz = new PredefinedQuiz(quizName);
        return predefinedQuizRepository.save(quiz);
    }

    /*======================================*
     * READ
     *======================================*/

    /**
     * Returns all predefined quizzes in a special DTO form so that the count of questions and the validity for
     * playing this quiz can be checked easier.
     *
     * @return all predefined quizzes
     */
    public List<PredefinedQuizDto> getAllPredefinedQuizzes() {

        // result list
        List<PredefinedQuizDto> responseQuizzes = new ArrayList<>();

        // fill result list with information from database
        Iterable<PredefinedQuiz> predefinedQuizzes = predefinedQuizRepository.findAll();
        for(PredefinedQuiz q : predefinedQuizzes) {
            int questionCount = predefinedQuizQuestionsRepository.findAllByQuizId(q.getId()).size();
            responseQuizzes.add(new PredefinedQuizDto(q.getId(), q.getQuizName(), questionCount));
        }

        // return the filled list
        return responseQuizzes;
    }

    /**
     * Returns an array of random question IDs.
     * <br />
     * If the question does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param questionCount the count of questions to be returned
     * @param alreadyUsedQuestions (optional) already used question IDs
     * @param locale the locale of the user
     *
     * @return the requested question as DTO for the user if the request is valid
     */
    public List<Long> getRandomQuiz(int questionCount, List<Long> alreadyUsedQuestions, Locale locale) {

        // result list
        List<Long> selectedQuestionIds = new ArrayList<>();

        // get all question IDs
        Iterable<Question> it = questionRepository.findAll();
        List<Long> allQuestions = new ArrayList<>();

        for(Question q : it) {
            allQuestions.add(q.getId());
        }

        // throw exception if not as much questions as wished
        if (allQuestions.size() < questionCount) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuizService.NotEnoughQuestions", null, locale));
        }

        // shuffle questions for more randomization
        Collections.shuffle(allQuestions);

        // fill result list with unused questions
        for(Long id : allQuestions) {
            // add unused question
            if (!alreadyUsedQuestions.contains(id)) {
                selectedQuestionIds.add(id);
            }

            // stop when wished question count is reached
            if (selectedQuestionIds.size() == questionCount) {
                break;
            }
        }

        // if there are not yet <questionCount> selected questions there have to be added some questions that
        // were already asked
        int remainingQuestionCount = questionCount - selectedQuestionIds.size();
        if (remainingQuestionCount > 0) {
            allQuestions.removeAll(selectedQuestionIds);
            for (int i = 0; i < remainingQuestionCount; i++) {
                selectedQuestionIds.add(allQuestions.get(i));
            }
        }

        // return question IDs array
        return selectedQuestionIds;
    }

    /**
     * Returns an array of question IDs which are assigned to the predefined quiz identified by the given ID.
     * <br />
     * If the predefined quiz does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param quizId the ID of the predefined quiz
     * @param locale the locale of the user
     *
     * @return the requested question as DTO for the user if the request is valid
     */
    public List<Long> getPredefinedQuiz(Long quizId, Locale locale) {

        // result list
        List<Long> questionIds = new ArrayList<>();

        // get the predefined quiz
        // => throw exception if not existing
        Optional<PredefinedQuiz> quiz = predefinedQuizRepository.findById(quizId);

        if (quiz.isEmpty()) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuizService.PredefinedQuizNotFound", null, locale));
        }

        // get the question IDs of the predefined quiz
        List<PredefinedQuizQuestion> relatedQuestions = predefinedQuizQuestionsRepository.findAllByQuizId(quizId);
        for(PredefinedQuizQuestion q : relatedQuestions) {
            questionIds.add(q.getQuestionId());
        }

        // return the result list of question IDs
        return questionIds;
    }

    /*======================================*
     * UPDATE
     *======================================*/

    /**
     * Updates the predefined quiz given by its ID with the given data.
     * <br />
     * If the predefined quiz does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param quizId the ID of the quiz to update
     * @param quizName the (updated) name of the quiz
     * @param quizQuestions the (updated) questions of the quiz
     * @param locale the locale of the user
     */
    public void updatePredefinedQuiz(Long quizId, String quizName, List<Long> quizQuestions, Locale locale) {

        // get the requested quiz from the database if existing
        // => if not existing throw an exception
        Optional<PredefinedQuiz> quiz = predefinedQuizRepository.findById(quizId);

        if (quiz.isEmpty()) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuizService.PredefinedQuizNotFound", null, locale));
        }

        // check if all given question IDs are existing
        // => if any ID is not existing throw an exception
        for(Long questionId : quizQuestions) {
            if (questionRepository.findById(questionId).isEmpty()) {
                throw new DataNotFoundException(this.messageSource.getMessage("QuizService.GivenQuestionNotFound", null, locale));
            }
        }

        // update the quiz name
        quiz.get().setQuizName(quizName);
        predefinedQuizRepository.save(quiz.get());

        // delete current questions of the quiz
        List<PredefinedQuizQuestion> currentQuestions = predefinedQuizQuestionsRepository.findAllByQuizId(quizId);
        predefinedQuizQuestionsRepository.deleteAll(currentQuestions);

        // add new questions (which are existing because of the check above)
        for(Long questionId : quizQuestions) {
            PredefinedQuizQuestion q = new PredefinedQuizQuestion(quizId, questionId);
            predefinedQuizQuestionsRepository.save(q);
        }
    }

    /*======================================*
     * DELETE
     *======================================*/

    /**
     * Deletes the predefined quiz with the given ID from the database.
     * <br />
     * If the quiz does not exist, a {@link DataNotFoundException} will be thrown.
     *
     * @param quizId the ID of the quiz to delete
     * @param locale the locale of the user
     */
    public void deletePredefinedQuiz(Long quizId, Locale locale) {

        // check if the quiz to be deleted is actual existing
        // => if not existing throw an exception
        boolean quizExists = predefinedQuizRepository.existsById(quizId);

        if (!quizExists) {
            throw new DataNotFoundException(this.messageSource.getMessage("QuizService.NotFound", null, locale));
        }

        // delete quiz
        this.predefinedQuizRepository.deleteById(quizId);

        // delete all question associations with the quiz
        this.predefinedQuizQuestionsRepository.deleteAllByQuizId(quizId);
    }
}
