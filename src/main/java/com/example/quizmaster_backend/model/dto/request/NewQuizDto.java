package com.example.quizmaster_backend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewQuizDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @Min(value = 10, message = "{NewQuizDto.questionCount.min10}")
    @Max(value = 10, message = "{NewQuizDto.questionCount.max30}")
    private int questionCount;

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
