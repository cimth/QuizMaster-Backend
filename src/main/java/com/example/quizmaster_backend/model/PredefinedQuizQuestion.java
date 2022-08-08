package com.example.quizmaster_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "predefined_quiz_questions")
public class PredefinedQuizQuestion {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long quizId;

    @NotNull
    private Long questionId;

    /*======================================*
     * CONSTRUCTORS
     *======================================*/

    public PredefinedQuizQuestion() {}

    /**
     * Applies the given question to the given predefined quiz.
     *
     * @param quizId the quiz to associate the question with
     * @param questionId the applied question
     */
    public PredefinedQuizQuestion(
            @NotNull Long quizId, 
            @NotNull Long questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
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
                .append("quizId", quizId)
                .append("questionId", questionId);

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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }    
}
