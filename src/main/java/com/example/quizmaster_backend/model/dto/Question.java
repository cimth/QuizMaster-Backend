package com.example.quizmaster_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String questionText;

    @NotNull
    private String correctAnswer;

    @NotNull
    private String wrongAnswer1;

    @NotNull
    private String wrongAnswer2;

    @NotNull
    private String wrongAnswer3;

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
                .append("questionText", questionText)
                .append("correctAnswer", correctAnswer)
                .append("wrongAnswer1", wrongAnswer1)
                .append("wrongAnswer2", wrongAnswer2)
                .append("wrongAnswer3", wrongAnswer3);

        return tsc.toString();
    }
}
