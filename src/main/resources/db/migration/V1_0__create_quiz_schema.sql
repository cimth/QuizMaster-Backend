# MYSQL

CREATE TABLE quiz_state(
     id                      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
     questionCount           INT NOT NULL,
     questionNo              INT NOT NULL,
     correctAnswers          INT NOT NULL,
     wrongAnswers            INT NOT NULL,
     session                 BIGINT NOT NULL
);