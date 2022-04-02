package com.example.quizmaster_backend.model.dto.request;

import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

public class NewPredefinedQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotEmpty(message = "{NewOrUpdatePredefinedQuizDto.quizName.NotEmpty}")
    private String quizName;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public NewPredefinedQuizDto() {}

    public NewPredefinedQuizDto(
            @NotEmpty(message = "{NewOrUpdatePredefinedQuizDto.quizName.NotEmpty}") String quizName) {
        this.quizName = quizName;
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
        tsc.append("quizName", quizName);

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
