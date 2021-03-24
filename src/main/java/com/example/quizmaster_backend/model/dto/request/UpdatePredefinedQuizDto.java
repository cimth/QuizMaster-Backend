package com.example.quizmaster_backend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePredefinedQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotEmpty(message = "{NewOrUpdatePredefinedQuizDto.quizName.NotEmpty}")
    private String quizName;

    @NotNull(message = "{UpdatePrefefinedQuizDto.quizQuestions.NotNull}")
    @Size(min = 10, max = 30, message = "{UpdatePrefefinedQuizDto.quizQuestions.Size}")
    private List<Long> quizQuestions;

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
}
