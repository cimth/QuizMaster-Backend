package com.example.quizmaster_backend.repository;

import com.example.quizmaster_backend.model.PredefinedQuiz;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PredefinedQuizRepository extends CrudRepository<PredefinedQuiz, Long> {
    // CRUD methods are automatically implemented through CrudRepository

    // add additional methods (implementation will be done automatically by Spring)
    public Optional<PredefinedQuiz> findByQuizName(String quizName);
}
