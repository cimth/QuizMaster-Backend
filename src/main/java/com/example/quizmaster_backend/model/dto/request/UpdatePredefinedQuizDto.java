package com.example.quizmaster_backend.model.dto.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

public class UpdatePredefinedQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotEmpty(message = "{NewOrUpdatePredefinedQuizDto.quizName.NotEmpty}")
    private String quizName;

    @NotNull(message = "{UpdatePrefefinedQuizDto.quizQuestions.NotNull}")
    private List<Long> quizQuestions;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public UpdatePredefinedQuizDto() {}

    public UpdatePredefinedQuizDto(
            @NotEmpty(message = "{NewOrUpdatePredefinedQuizDto.quizName.NotEmpty}") String quizName,
            @NotNull(message = "{UpdatePrefefinedQuizDto.quizQuestions.NotNull}") List<Long> quizQuestions) {
        this.quizName = quizName;
        this.quizQuestions = quizQuestions;
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
        tsc.append("quizName", quizName)
                .append("quizQuestions", quizQuestions);

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

    public List<Long> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<Long> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }
}
