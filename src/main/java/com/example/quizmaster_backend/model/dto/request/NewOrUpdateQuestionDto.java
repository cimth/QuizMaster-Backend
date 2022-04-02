package com.example.quizmaster_backend.model.dto.request;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.core.style.ToStringCreator;

public class NewOrUpdateQuestionDto {

    /*======================================*
     * FIELDS
     *======================================*/
    
    @NotEmpty(message = "{NewOrUpdateQuestionDto.questionText.NotEmpty}")
    private String questionText;

    @NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}")
    private String correctAnswer;

    @NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}")
    @Size(min = 3, max = 3, message = "{NewOrUpdateQuestionDto.wrongAnswers.size}")
    private List<@NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}") String> wrongAnswers;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    public NewOrUpdateQuestionDto() {}

    public NewOrUpdateQuestionDto(
            @NotEmpty(message = "{NewOrUpdateQuestionDto.questionText.NotEmpty}") String questionText,
            @NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}") String correctAnswer,
            @NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}") @Size(min = 3, max = 3, message = "{NewOrUpdateQuestionDto.wrongAnswers.size}") List<@NotEmpty(message = "{NewOrUpdateQuestionDto.answers.NotEmpty}") String> wrongAnswers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
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
        tsc.append("questionText", questionText)
                .append("correctAnswer", correctAnswer)
                .append("wrongAnswers", Arrays.toString(wrongAnswers.toArray()));

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
