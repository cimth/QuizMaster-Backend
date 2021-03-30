package com.example.quizmaster_backend.repository;

import com.example.quizmaster_backend.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    // CRUD methods are automatically implemented through CrudRepository
}
