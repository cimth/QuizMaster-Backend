package com.example.quizmaster_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "question")
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
     * CONSTRUCTORS
     *======================================*/

    public Question() {}

    /**
     * Creates a question with all fields but an ID.
     * @param questionText the text of the question
     * @param correctAnswer the text of the correct answer
     * @param wrongAnswer1 the text of one wrong answer
     * @param wrongAnswer2 the text of one wrong answer
     * @param wrongAnswer3 the text of one wrong answer
     */
    public Question(
            @NotNull String questionText, 
            @NotNull String correctAnswer, 
            @NotNull String wrongAnswer1,
            @NotNull String wrongAnswer2, 
            @NotNull String wrongAnswer3) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
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
                .append("questionText", questionText)
                .append("correctAnswer", correctAnswer)
                .append("wrongAnswer1", wrongAnswer1)
                .append("wrongAnswer2", wrongAnswer2)
                .append("wrongAnswer3", wrongAnswer3);

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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    
}
