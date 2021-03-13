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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrUpdatedQuestionDto {

    /*======================================*
     * FIELDS
     *======================================*/
    
    @NotEmpty(message = "{NewOrUpdatedQuestionDto.question.NotEmpty}")
    private String questionText;

    @NotEmpty(message = "{NewOrUpdatedQuestionDto.answers.NotEmpty}")
    private String correctAnswer;

    @NotEmpty(message = "{NewOrUpdatedQuestionDto.answers.NotEmpty}")
    @Size(min = 3, max = 3, message = "{NewOrUpdatedQuestionDto.wrongAnswers.size}")
    private List<@NotEmpty(message = "{NewOrUpdatedQuestionDto.answers.NotEmpty}") String> wrongAnswers;

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
}
