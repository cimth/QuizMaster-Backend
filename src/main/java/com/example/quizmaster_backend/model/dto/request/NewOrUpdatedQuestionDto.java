package com.example.quizmaster_backend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrUpdatedQuestionDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull(message = "{NewOrUpdatedQuestionDto.NotNull}")
    @NotEmpty(message = "{NewOrUpdatedQuestionDto.NotEmpty}")
    private String correctAnswer;

    @NotNull(message = "{NewOrUpdatedQuestionDto.NotNull}")
    @NotEmpty(message = "{NewOrUpdatedQuestionDto.NotEmpty}")
    @Size(min = 3, max = 3, message = "{NewOrUpdatedQuestionDto.wrongAnswers.size}")
    private String[] wrongAnswers;

    /*======================================*
     * STRING REPRESENTATION
     *======================================*/

    /**
     * @return a string representation of the calling instance
     */
    @Override
    public String toString() {

        ToStringCreator tsc = new ToStringCreator(this);
        tsc.append("correctAnswer", correctAnswer)
                .append("wrongAnswers", Arrays.toString(wrongAnswers));

        return tsc.toString();
    }
}
