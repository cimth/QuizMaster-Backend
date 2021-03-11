# MYSQL

CREATE TABLE session(
     id                      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
     alreadyUsedQuestions    TEXT NOT NULL        # List of question IDs formatted as JSON
);