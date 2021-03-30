package com.example.quizmaster_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "predefined_quiz_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
     * CUSTOM CONSTRUCTOR
     *======================================*/

    /**
     * Applies the given question to the given predefined quiz.
     *
     * @param quizId the quiz to associate the question with
     * @param questionId the applied question
     */
    public PredefinedQuizQuestion(@NotNull Long quizId, @NotNull Long questionId) {
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
}
