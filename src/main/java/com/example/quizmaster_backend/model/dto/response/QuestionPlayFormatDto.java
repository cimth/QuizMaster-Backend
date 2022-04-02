package com.example.quizmaster_backend.model.dto.response;

import java.util.Arrays;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.quizmaster_backend.model.AnswerLetter;

import org.springframework.core.style.ToStringCreator;

public class QuestionPlayFormatDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String questionText;

    @NotNull
    @Size(min = 4, max = 4)
    private PossibleAnswerDto[] possibleAnswers;

    @NotNull
    private AnswerLetter correctAnswer;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public QuestionPlayFormatDto() {}

    public QuestionPlayFormatDto(
            Long id, 
            @NotNull String questionText,
            @NotNull 
            @Size(min = 4, max = 4) PossibleAnswerDto[] possibleAnswers, 
            @NotNull AnswerLetter correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    /*======================================*
     * STRING REPRESENTATION
     *======================================*/

    /**
     * @return a string representation of the calling instance
     */
    @Override
    public String toString() {

        ToStringCreator tsc = new ToStringCreator(this);
        tsc.append("id", id)
            .append("questionText", questionText)
            .append("possibleAnswers", Arrays.toString(possibleAnswers))
            .append("correctAnswer", correctAnswer.toString());

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public PossibleAnswerDto[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(PossibleAnswerDto[] possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public AnswerLetter getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerLetter correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
