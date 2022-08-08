package com.example.quizmaster_backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.quizmaster_backend.model.PredefinedQuizQuestion;

import org.springframework.data.repository.CrudRepository;

public interface PredefinedQuizQuestionsRepository extends CrudRepository<PredefinedQuizQuestion, Long> {
    // CRUD methods are automatically implemented through CrudRepository

    // add additional methods (implementation will be done automatically by Spring)
    List<PredefinedQuizQuestion> findAllByQuizId(Long quizId);

    @Transactional
    void deleteAllByQuizId(Long quizId);

    @Transactional
    boolean existsByQuestionId(Long questionId);
}
