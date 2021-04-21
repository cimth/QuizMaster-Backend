package com.example.quizmaster_backend.model.dto.response;

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
}
