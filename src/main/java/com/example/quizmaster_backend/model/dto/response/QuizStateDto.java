package com.example.quizmaster_backend.model.dto.response;

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
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizStateDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 10)
    @Max(value = 30)
    private int questionCount;

    @Min(value = 1)
    @Max(value = 30)
    private int questionNo;

    @Min(value = 0)
    @Max(value = 30)
    private int correctAnswers;

    @Min(value = 0)
    @Max(value = 30)
    private int wrongAnswers;

    /*======================================*
     * STRING REPRESENTATION
     *======================================*/

    /**
     * @return a string representation of the calling instance
     */
    @Override
    public String toString() {

        ToStringCreator tsc = new ToStringCreator(this);
        tsc.append("id", id)
                .append("questionCount", questionCount)
                .append("questionNo", questionNo)
                .append("correctAnswers", correctAnswers)
                .append("wrongAnswers", wrongAnswers);

        return tsc.toString();
    }
}
