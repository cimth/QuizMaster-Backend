# MYSQL

CREATE TABLE question(
     id                      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
     correctAnswer           TEXT NOT NULL,
     wrongAnswer1            TEXT NOT NULL,
     wrongAnswer2            TEXT NOT NULL,
     wrongAnswer3            TEXT NOT NULL
);