package com.example.quizmaster_backend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull(message = "{NewQuizDto.questionCount.NotNull}")
    @Min(value = 10, message = "{NewQuizDto.questionCount.min10}")
    @Max(value = 30, message = "{NewQuizDto.questionCount.max30}")
    private Integer questionCount;

    private List<Long> alreadyUsedQuestions;

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
}
