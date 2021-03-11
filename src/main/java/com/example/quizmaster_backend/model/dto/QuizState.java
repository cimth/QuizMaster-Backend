package com.example.quizmaster_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizState {

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

    @NotNull
    private Long session;

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
            .append("wrongAnswers", wrongAnswers)
            .append("session", session);

        return tsc.toString();
    }
}
