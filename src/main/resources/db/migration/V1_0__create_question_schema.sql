// H2

CREATE TABLE question(
     id                      IDENTITY NOT NULL,
     questionText            CLOB NOT NULL,
     correctAnswer           CLOB NOT NULL,
     wrongAnswer1            CLOB NOT NULL,
     wrongAnswer2            CLOB NOT NULL,
     wrongAnswer3            CLOB NOT NULL
);