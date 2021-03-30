package com.example.quizmaster_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "predefined_quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedQuiz {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String quizName;

    /*======================================*
     * CUSTOM CONSTRUCTOR
     *======================================*/

    /**
     * Creates a predefined quiz with the given name and an automatically assigned ID.
     *
     * @param quizName the name of the predefined quiz
     */
    public PredefinedQuiz(@NotNull String quizName) {
        this.quizName = quizName;
    }

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
                .append("quizName", quizName);

        return tsc.toString();
    }
}
