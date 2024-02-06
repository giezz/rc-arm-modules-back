INSERT INTO doc.scale (name, description)
VALUES ('Шкала A', 'Описание шкалы A'),
       ('Шкала B', 'Описание шкалы B'),
       ('Шкала C', 'Описание шкалы C');
-- Добавление данных в таблицу doc.question
INSERT INTO doc.question (content)
VALUES ('Вопрос 1'),
       ('Вопрос 2'),
       ('Вопрос 3');

INSERT INTO doc.exercise_type (name)
VALUES ('Тип упражнения 1'),
       ('Тип упражнения 2'),
       ('Тип упражнения 3');

INSERT INTO doc.variant (content, score, question_id)
VALUES ('Вариант 1', 5.0, 4),
       ('Вариант 2', 3.0, 4),
       ('Вариант 3', 2.5, 4),
       ('Вариант 1', 4.0, 5),
       ('Вариант 2', 2.0, 5),
       ('Вариант 3', 1.5, 5);
-- Добавление данных в таблицу doc.form
INSERT INTO doc.form (name, description, scale_id)
VALUES ('Форма 1', 'Описание формы 1', 1),
       ('Форма 2', 'Описание формы 2', 2),
       ('Форма 3', 'Описание формы 3', 3);

-- Добавление данных в таблицу doc.exercise
INSERT INTO doc.exercise (name, video_url, description, exercise_type_id)
VALUES ('Упражнение 1', 'https://www.example.com/video1', 'Описание упражнения 1', 1),
       ('Упражнение 2', 'https://www.example.com/video2', 'Описание упражнения 2', 2),
       ('Упражнение 3', 'https://www.example.com/video3', 'Описание упражнения 3', 3);


-- Добавление связей между формами и вопросами в таблицу doc.form_question
INSERT INTO doc.form_question (id_form, id_question)
VALUES (4, 1),
       (5, 2),
       (5, 2),
       (6, 3);

