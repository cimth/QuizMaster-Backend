package com.example.quizmaster_backend.model.dto.response;

import com.example.quizmaster_backend.model.dto.AnswerLetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    /*======================================*
     * FIELDS
     *======================================*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String questionText;

    @NotNull
    @Size(min = 4, max = 4)
    private PossibleAnswerDto[] possibleAnswers;

    @NotNull
    private AnswerLetter correctAnswer;

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
            .append("possibleAnswers", Arrays.toString(possibleAnswers))
            .append("correctAnswer", correctAnswer.toString());

        return tsc.toString();
    }
}
