package com.example.quizmaster_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "predefined_quiz")
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
     * CONSTRUCTORS
     *======================================*/

    public PredefinedQuiz() {}

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

    /*======================================*
     * ACCESSORS
     *======================================*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
