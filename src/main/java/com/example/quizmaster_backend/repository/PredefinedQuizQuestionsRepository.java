package com.example.quizmaster_backend.repository;

import com.example.quizmaster_backend.model.PredefinedQuiz;
import com.example.quizmaster_backend.model.PredefinedQuizQuestion;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PredefinedQuizQuestionsRepository extends CrudRepository<PredefinedQuizQuestion, Long> {
    // CRUD methods are automatically implemented through CrudRepository

    // add additional methods (implementation will be done automatically by Spring)
    List<PredefinedQuizQuestion> findAllByQuizId(Long quizId);

    @Transactional
    void deleteAllByQuizId(Long quizId);

    boolean findByQuestionId(Long questionId);
}
