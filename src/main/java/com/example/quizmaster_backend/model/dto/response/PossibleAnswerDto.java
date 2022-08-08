package com.example.quizmaster_backend.model.dto.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.quizmaster_backend.model.AnswerLetter;

import org.springframework.core.style.ToStringCreator;

public class PossibleAnswerDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull
    private AnswerLetter answerLetter;

    @NotNull
    @NotEmpty
    private String answerText;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public PossibleAnswerDto() {}

    public PossibleAnswerDto(
            @NotNull AnswerLetter answerLetter, 
            @NotNull @NotEmpty String answerText) {
        this.answerLetter = answerLetter;
        this.answerText = answerText;
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
        tsc.append("answerLetter", answerLetter.toString())
                .append("answerText", answerText);

        return tsc.toString();
    }

    /*======================================*
     * ACCESSORS
     *======================================*/

    public AnswerLetter getAnswerLetter() {
        return answerLetter;
    }

    public void setAnswerLetter(AnswerLetter answerLetter) {
        this.answerLetter = answerLetter;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
