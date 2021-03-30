// H2

CREATE TABLE predefined_quiz(
    id          IDENTITY NOT NULL,
    quizName    CLOB NOT NULL
);

CREATE TABLE predefined_quiz_questions(
    id          IDENTITY NOT NULL,
    quizId      BIGINT NOT NULL,
    questionId  BIGINT NOT NULL
);