package com.example.quizmaster_backend.model.dto.response;

import com.example.quizmaster_backend.model.AnswerLetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
