package com.example.quizmaster_backend.model.dto.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

public class PredefinedQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull
    private Long quizId;

    @NotEmpty
    private String quizName;

    private int questionCount;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public PredefinedQuizDto() {}

    public PredefinedQuizDto(
            @NotNull Long quizId, 
            @NotEmpty String quizName, 
            int questionCount) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questionCount = questionCount;
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
        tsc.append("quizId", quizId)
                .append("quizName", quizName)
                .append("questionCount", questionCount);

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
