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
public class NewRandomQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @NotNull(message = "{NewRandomQuizDto.questionCount.NotNull}")
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
