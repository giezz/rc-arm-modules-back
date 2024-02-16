--
-- Data for Name: _role; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc._role (id, name) VALUES (1, 'ADMIN');
INSERT INTO doc._role (id, name) VALUES (2, 'DOCTOR');

--
-- Data for Name: _user; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc._user (id, login, password) VALUES (1, 'admin', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');
INSERT INTO doc._user (id, login, password) VALUES (2, 'user', '$2a$12$Kwjwg2cajJef3S/8uCWPwuTg8PAXTDuJ9TPXZ66LVXpegbgSbvsGW');

--
-- Data for Name: user_role; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.user_role (_user_id, _role_id) VALUES (1, 2);
INSERT INTO doc.user_role (_user_id, _role_id) VALUES (1, 1);
INSERT INTO doc.user_role (_user_id, _role_id) VALUES (2, 1);
INSERT INTO doc.user_role (_user_id, _role_id) VALUES (2, 2);

--
-- Data for Name: doctor; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.doctor (id, doctor_code, first_name, middle_name, last_name, email, phone_number, _user_id) VALUES (1, 100, 'Михаил', 'Алексеевич', 'Батухтин', 'giezz@vk.com', '777777', 1);
INSERT INTO doc.doctor (id, doctor_code, first_name, middle_name, last_name, email, phone_number, _user_id) VALUES (2, 101, 'Тест', 'Тестович', 'Тестов', 'test@vk.com', '777771', 2);

--
-- Data for Name: passport; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (1, '2345', '678901', 'Отделение УФМС по г. Санкт-Петербургу', '2012-08-20');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (2, '3456', '789012', 'Отделение УФМС по г. Екатеринбургу', '2014-02-10');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (3, '4567', '890123', 'Отделение УФМС по г. Нижний Новгороду', '2007-11-05');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (4, '5678', '901234', 'Отделение УФМС по г. Казани', '2015-06-15');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (5, '6789', '12345 ', 'Отделение УФМС по г. Владивостоку', '2018-03-25');

--
-- Data for Name: patient_status; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.patient_status (id, name) VALUES (1, 'Нуждается в реабилитации');
INSERT INTO doc.patient_status (id, name) VALUES (2, 'Проходит реабилитацию');
INSERT INTO doc.patient_status (id, name) VALUES (3, 'Проходил реабилитацию ранее');

--
-- Data for Name: patient; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id, doctor_id, _user_id) VALUES (4, 1008, 'Иван', 'Дмитриевич', 'Петров', '1988-09-10', NULL, 'пр. Ленинградский, д. 25, кв. 7, Казань', '5554444', 'Лукойл', NULL, '56789012345', '6789012345678901', 3, 4, NULL, 2);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id, doctor_id, _user_id) VALUES (5, 1009, 'Мария', 'Андреевна', 'Новикова', '1995-04-27', NULL, 'ул. Красная, д. 18, кв. 6, Владивосток', '5555555', 'Сбербанк', NULL, '67890123456', '7890123456789012', 2, 5, NULL, 2);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id, doctor_id, _user_id) VALUES (2, 1006, 'Андрей', 'Валерьевич', 'Сидоров', '1980-12-05', NULL, 'пр. Победы, д. 15, кв. 3, Екатеринбург', '5552222', 'Транснефть', NULL, '34567890123', '4567890123456789', 2, 2, 1, 2);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id, doctor_id, _user_id) VALUES (1, 1005, 'Елена', 'Петровна', 'Иванова', '1992-03-18', NULL, 'ул. Ленина, д. 20, кв. 8, Санкт-Петербург', '5551111', 'Газпром', NULL, '23456789012', '3456789012345678', 1, 1, NULL, 2);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id, doctor_id, _user_id) VALUES (3, 1007, 'Ольга', 'Сергеевна', 'Кузнецова', '1975-06-30', NULL, 'ул. Гагарина, д. 8, кв. 12, Нижний Новгород', '5553333', 'РЖД', NULL, '45678901234', '5678901234567890', 1, 3, 1, 2);

--
-- Data for Name: block; Type: TABLE DATA; Schema: doc; Owner: postgres
--

INSERT INTO doc.block (id, name) VALUES (1, 'Разминка');
INSERT INTO doc.block (id, name) VALUES (2, 'Основной блок');
INSERT INTO doc.block (id, name) VALUES (3, 'Заминка');

--
-- Data for Name: exercise_type; Type: TABLE DATA; Schema: doc; Owner: postgres
--


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
VALUES ('Вариант 1', 5.0, 1),
       ('Вариант 2', 3.0, 1),
       ('Вариант 3', 2.5, 1),
       ('Вариант 1', 4.0, 2),
       ('Вариант 2', 2.0, 2),
       ('Вариант 3', 1.5, 2);
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
       (5, 3),
       (6, 3);