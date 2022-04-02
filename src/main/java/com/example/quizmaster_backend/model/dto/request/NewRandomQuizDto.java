package com.example.quizmaster_backend.model.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

public class NewRandomQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull(message = "{NewRandomQuizDto.questionCount.NotNull}")
    private Integer questionCount;

    private List<Long> alreadyUsedQuestions;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    public NewRandomQuizDto() {}

    public NewRandomQuizDto(
            @NotNull(message = "{NewRandomQuizDto.questionCount.NotNull}") Integer questionCount,
            List<Long> alreadyUsedQuestions) {
        this.questionCount = questionCount;
        this.alreadyUsedQuestions = alreadyUsedQuestions;
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
        tsc.append("questionCount", questionCount);

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public List<Long> getAlreadyUsedQuestions() {
        return alreadyUsedQuestions;
    }

    public void setAlreadyUsedQuestions(List<Long> alreadyUsedQuestions) {
        this.alreadyUsedQuestions = alreadyUsedQuestions;
    }
}
