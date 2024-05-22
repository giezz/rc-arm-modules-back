--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2024-05-22 18:11:09

--
-- TOC entry 3541 (class 0 OID 44565)
-- Dependencies: 237
-- Data for Name: scale; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.scale (id, name, description)
VALUES (1, 'Шкала A', 'Описание шкалы A');
INSERT INTO anketi.scale (id, name, description)
VALUES (2, 'Шкала B', 'Описание шкалы B');
INSERT INTO anketi.scale (id, name, description)
VALUES (3, 'Шкала C', 'Описание шкалы C');


--
-- TOC entry 3521 (class 0 OID 44461)
-- Dependencies: 217
-- Data for Name: form; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form (id, scale_id, name, description)
VALUES (1, 3, 'Тестовая анкета 3', 'Описание');
INSERT INTO anketi.form (id, scale_id, name, description)
VALUES (2, 1, 'Погода', 'Анкета с двумя вопросами');
INSERT INTO anketi.form (id, scale_id, name, description)
VALUES (3, 2, 'Что Вы ели', 'Анкета с тремя вопросами');


--
-- TOC entry 3537 (class 0 OID 44548)
-- Dependencies: 233
-- Data for Name: question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.question (id, content, required, type)
VALUES (1, 'Как у Вас дела?', false, 'SINGLE_CHOICE');
INSERT INTO anketi.question (id, content, required, type)
VALUES (2, 'Какая сегодня погода?', false, 'MULTIPLE_CHOICE');
INSERT INTO anketi.question (id, content, required, type)
VALUES (3, 'Что Вы ели на завтрак?', false, 'SINGLE_CHOICE');
INSERT INTO anketi.question (id, content, required, type)
VALUES (4, 'Что Вы ели на обед?', false, 'SINGLE_CHOICE');
INSERT INTO anketi.question (id, content, required, type)
VALUES (5, 'Что Вы ели на ужин', false, 'SINGLE_CHOICE');


--
-- TOC entry 3556 (class 0 OID 44684)
-- Dependencies: 252
-- Data for Name: form_question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form_question (id, question_id, form_id, created_at)
VALUES (1, 1, 1, '2024-03-04 19:07:15');
INSERT INTO anketi.form_question (id, question_id, form_id, created_at)
VALUES (2, 2, 1, '2024-03-04 19:07:17');
INSERT INTO anketi.form_question (id, question_id, form_id, created_at)
VALUES (3, 3, 2, '2024-03-04 19:07:18');
INSERT INTO anketi.form_question (id, question_id, form_id, created_at)
VALUES (4, 4, 2, '2024-03-04 19:07:19');
INSERT INTO anketi.form_question (id, question_id, form_id, created_at)
VALUES (5, 5, 2, '2024-03-04 19:07:20');


--
-- TOC entry 3547 (class 0 OID 44612)
-- Dependencies: 243
-- Data for Name: interpretation; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (1, 1, 1.00, 5.00, 'Отлично');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (2, 1, 6.00, 10.00, 'Хорошо');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (3, 1, 11.00, 15.00, 'Отлично');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (4, 1, 16.00, 20.00, 'Кашу');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (5, 2, 0.00, 5.00, 'scale B. BAD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (6, 2, 6.00, 10.00, 'scale B. GOOD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (7, 3, 0.00, 5.00, 'scale C. BAD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description)
VALUES (8, 3, 6.00, 10.00, 'scale C. GOOD');


--
-- TOC entry 3545 (class 0 OID 44593)
-- Dependencies: 241
-- Data for Name: variant; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (1, 2, 1.50, 'Пасмурно');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (2, 2, 4.00, 'Ясная');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (3, 1, 2.50, 'Не очень');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (4, 2, 2.00, 'Местами облачность');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (5, 1, 3.00, 'Хорошо');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (6, 1, 5.00, 'Отлично');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (7, 3, 1.00, 'Кашу');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (8, 3, 0.00, 'Ничего');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (9, 4, 1.00, 'Суп');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (10, 4, 0.00, 'Ничего');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (11, 5, 1.00, 'Рис');
INSERT INTO anketi.variant (id, question_id, score, content)
VALUES (12, 5, 0.00, 'Ничего');


--
-- TOC entry 3551 (class 0 OID 44635)
-- Dependencies: 247
-- Data for Name: _role; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm._role (id, name)
VALUES (1, 'ADMIN');
INSERT INTO arm._role (id, name)
VALUES (2, 'DOCTOR');
INSERT INTO arm._role (id, name)
VALUES (3, 'PATIENT');


--
-- TOC entry 3549 (class 0 OID 44626)
-- Dependencies: 245
-- Data for Name: _user; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm._user (id, login, password)
VALUES (1, 'admin', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');
INSERT INTO arm._user (id, login, password)
VALUES (2, 'user', '$2a$12$Kwjwg2cajJef3S/8uCWPwuTg8PAXTDuJ9TPXZ66LVXpegbgSbvsGW');
INSERT INTO arm._user (id, login, password)
VALUES (3, 'patient', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');


--
-- TOC entry 3531 (class 0 OID 44509)
-- Dependencies: 227
-- Data for Name: block; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.block (id, name)
VALUES (1, 'Разминка');
INSERT INTO arm.block (id, name)
VALUES (2, 'Основной блок');
INSERT INTO arm.block (id, name)
VALUES (3, 'Заминка');


--
-- TOC entry 3517 (class 0 OID 44439)
-- Dependencies: 213
-- Data for Name: doctor; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.doctor (id, doctor_code, _user_id, first_name, middle_name, last_name, email, phone_number)
VALUES (1, 100, 1, 'Михаил', 'Алексеевич', 'Батухтин', 'giezz@vk.com', '777777');
INSERT INTO arm.doctor (id, doctor_code, _user_id, first_name, middle_name, last_name, email, phone_number)
VALUES (2, 101, 2, 'Тест', 'Тестович', 'Тестов', 'test@vk.com', '777771');


--
-- TOC entry 3543 (class 0 OID 44579)
-- Dependencies: 239
-- Data for Name: exercise_type; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.exercise_type (id, name)
VALUES (1, 'Тип упражнения 1');
INSERT INTO arm.exercise_type (id, name)
VALUES (2, 'Тип упражнения 2');
INSERT INTO arm.exercise_type (id, name)
VALUES (3, 'Тип упражнения 3');


--
-- TOC entry 3523 (class 0 OID 44470)
-- Dependencies: 219
-- Data for Name: exercise; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.exercise (id, exercise_type_id, video_url, name, description)
VALUES (1, 1, 'https://www.example.com/video1', 'Упражнение 1', 'Описание упражнения 1');
INSERT INTO arm.exercise (id, exercise_type_id, video_url, name, description)
VALUES (2, 2, 'https://www.example.com/video2', 'Упражнение 2', 'Описание упражнения 2');
INSERT INTO arm.exercise (id, exercise_type_id, video_url, name, description)
VALUES (3, 3, 'https://www.example.com/video3', 'Упражнение 3', 'Описание упражнения 3');


--
-- TOC entry 3535 (class 0 OID 44532)
-- Dependencies: 231
-- Data for Name: passport; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (1, '2345', '678901', '2012-08-20', 'Отделение УФМС по г. Санкт-Петербургу');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (2, '3456', '789012', '2014-02-10', 'Отделение УФМС по г. Екатеринбургу');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (3, '4567', '890123', '2007-11-05', 'Отделение УФМС по г. Нижний Новгороду');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (4, '5678', '901234', '2015-06-15', 'Отделение УФМС по г. Казани');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (5, '6789', '12345 ', '2018-03-25', 'Отделение УФМС по г. Владивостоку');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (6, '7890', '23456 ', '2013-09-30', 'Отделение УФМС по г. Самаре');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (7, '8901', '34567 ', '2016-12-10', 'Отделение УФМС по г. Ростову-на-Дону');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (8, '9012', '45678 ', '2009-04-18', 'Отделение УФМС по г. Уфе');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (9, '0123', '56789 ', '2017-07-22', 'Отделение УФМС по г. Краснодару');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (10, '1234', '67890 ', '2011-10-05', 'Отделение УФМС по г. Челябинску');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (11, '2345', '78901 ', '2019-05-15', 'Отделение УФМС по г. Омску');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (12, '3456', '89012 ', '2008-08-28', 'Отделение УФМС по г. Тюмени');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (13, '4567', '90123 ', '2012-11-20', 'Отделение УФМС по г. Иркутску');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (14, '5678', '01234 ', '2015-04-03', 'Отделение УФМС по г. Хабаровску');
INSERT INTO arm.passport (id, series, number, issued_date, issued)
VALUES (15, '6789', '12345 ', '2010-07-08', 'Отделение УФМС по г. Волгограду');


--
-- TOC entry 3525 (class 0 OID 44479)
-- Dependencies: 221
-- Data for Name: status; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.status (id, name)
VALUES (1, 'Нуждается в реабилитации');
INSERT INTO arm.status (id, name)
VALUES (2, 'Проходит реабилитацию');
INSERT INTO arm.status (id, name)
VALUES (3, 'Проходил реабилитацию ранее');


--
-- TOC entry 3519 (class 0 OID 44450)
-- Dependencies: 215
-- Data for Name: patient; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (2, 1009, 2, 5, 1, 'Мария', 'Андреевна', 'Новикова', '5555555', '67890123456', '7890123456789012', 'f', NULL,
        '1995-04-27', 'ул. Красная, д. 18, кв. 6, Владивосток', NULL, 'Сбербанк');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (3, 1006, 2, 2, 1, 'Андрей', 'Валерьевич', 'Сидоров', '5552222', '34567890123', '4567890123456789', 'm', NULL,
        '1980-12-05', 'пр. Победы, д. 15, кв. 3, Екатеринбург', NULL, 'Транснефть');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (4, 1005, 2, 1, 1, 'Елена', 'Петровна', 'Иванова', '5551111', '23456789012', '3456789012345678', 'f', NULL,
        '1992-03-18', 'ул. Ленина, д. 20, кв. 8, Санкт-Петербург', NULL, 'Газпром');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (5, 1007, 3, 3, 1, 'Ольга', 'Сергеевна', 'Кузнецова', '5553333', '45678901234', '5678901234567890', 'f', NULL,
        '1975-06-30', 'ул. Гагарина, д. 8, кв. 12, Нижний Новгород', NULL, 'РЖД');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (6, 1010, 2, 6, 1, 'Тест', 'Тестович', 'Тестов', '5553331', '45678901231', '5678901234567891', 'f', NULL,
        '1975-06-30', 'Тестовый адрес', NULL, 'Тестовая работа');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (7, 1011, 2, 7, 1, 'Иван', 'Иванович', 'Иванов', '5553332', '45678901232', '5678901234567892', 'm', NULL,
        '1982-09-15', 'Улица Пушкина, дом Колотушкина', NULL, 'Менеджер');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (8, 1012, 2, 8, 1, 'Мария', 'Алексеевна', 'Сидорова', '5553333', '45678901233', '5678901234567893', 'f', NULL,
        '1990-03-22', 'Проезд Орлова, дом Петрова', NULL, 'Бухгалтер');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (9, 1013, 2, 9, 1, 'Петр', 'Семенович', 'Петров', '5553334', '45678901234', '5678901234567894', 'm', NULL,
        '1978-12-05', 'Проспект Ленина, дом Сталина', NULL, 'Инженер');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (10, 1014, 2, 10, 1, 'Елена', 'Васильевна', 'Сергеева', '5553335', '45678901235', '5678901234567895', 'f', NULL,
        '1985-05-20', 'Бульвар Гагарина, дом Ленина', NULL, 'Учитель');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (11, 1015, 2, 11, 1, 'Александр', 'Павлович', 'Кузнецов', '5553336', '45678901266', '5678901234567896', 'm',
        NULL, '1980-08-10', 'Улица Лермонтова, дом Пушкина', NULL, 'Врач');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (12, 1016, 2, 12, 1, 'Наталья', 'Дмитриевна', 'Васильева', '5553337', '45678901237', '5678901234567897', 'f',
        NULL, '1992-01-25', 'Переулок Тургенева, дом Чехова', NULL, 'Художник');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (13, 1017, 2, 13, 1, 'Дмитрий', 'Александрович', 'Козлов', '5553338', '45678901238', '2345678901234567', 'm',
        NULL, '1970-04-18', 'Проезд Пушкина, дом Гоголя', NULL, 'Администратор');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (14, 1018, 2, 14, 1, 'Анна', 'Сергеевна', 'Иванова', '5553339', '45678901239', '3456789012345678', 'f', NULL,
        '1988-07-12', 'Улица Горького, дом Достоевского', NULL, 'Менеджер по продажам');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (15, 1019, 2, 15, 1, 'Сергей', 'Николаевич', 'Смирнов', '5553340', '45678901230', '4567890123456789', 'm', NULL,
        '1977-10-30', 'Бульвар Ленина, дом Карла Маркса', NULL, 'Инженер-программист');
INSERT INTO arm.patient (id, patient_code, _user_id, passport_id, status_id, first_name, middle_name, last_name,
                         phone_number, snils, polis, gender, death_date, birth_date, address, bookmark, work_place_data)
VALUES (1, 1008, 2, 4, 2, 'Иван', 'Дмитриевич', 'Петров', '5554444', '56789012345', '6789012345678901', 'm', NULL,
        '1988-09-10', 'пр. Ленинградский, д. 25, кв. 7, Казань', NULL, 'Лукойл');


--
-- TOC entry 3527 (class 0 OID 44493)
-- Dependencies: 223
-- Data for Name: rehab_program; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.rehab_program (id, doctor_id, patient_id, is_current, created_at, start_date, end_date)
VALUES (1, 1, 1, true, '2024-05-22 15:02:55.865732', '2024-05-22 15:02:55.865732', NULL);


--
-- TOC entry 3529 (class 0 OID 44500)
-- Dependencies: 225
-- Data for Name: module; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.module (id, rehab_program_id, finished_at, name)
VALUES (1, 1, NULL, 'Модуль');


--
-- TOC entry 3554 (class 0 OID 44672)
-- Dependencies: 250
-- Data for Name: module_exercise; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.module_exercise (id, exercise_id, module_id, block_id, finished_at)
VALUES (1, 1, 1, 1, NULL);
INSERT INTO arm.module_exercise (id, exercise_id, module_id, block_id, finished_at)
VALUES (2, 1, 1, 2, NULL);
INSERT INTO arm.module_exercise (id, exercise_id, module_id, block_id, finished_at)
VALUES (3, 1, 1, 3, NULL);


--
-- TOC entry 3562 (class 0 OID 44750)
-- Dependencies: 258
-- Data for Name: module_form; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.module_form (id, module_id, form_id, finished_at, score)
VALUES (1, 1, 3, NULL, NULL);


--
-- TOC entry 3564 (class 0 OID 44772)
-- Dependencies: 260
-- Data for Name: module_form_answer; Type: TABLE DATA; Schema: arm; Owner: postgres
--


--
-- TOC entry 3560 (class 0 OID 44733)
-- Dependencies: 256
-- Data for Name: type; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.type (id, name)
VALUES (1, 'Вводная анкета');
INSERT INTO arm.type (id, name)
VALUES (2, 'Выходная анкета');


--
-- TOC entry 3558 (class 0 OID 44721)
-- Dependencies: 254
-- Data for Name: program_form; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.program_form (id, rehab_program_id, form_id, type_id, finished_at, score)
VALUES (1, 1, 3, 1, NULL, NULL);
INSERT INTO arm.program_form (id, rehab_program_id, form_id, type_id, finished_at, score)
VALUES (2, 1, 3, 2, NULL, NULL);


--
-- TOC entry 3539 (class 0 OID 44558)
-- Dependencies: 235
-- Data for Name: program_form_answer; Type: TABLE DATA; Schema: arm; Owner: postgres
--


--
-- TOC entry 3533 (class 0 OID 44518)
-- Dependencies: 229
-- Data for Name: protocol; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.protocol (id, rehab_program_id, creation_date, is_final, scales_result, rehab_result, recommendations,
                          rehab_diagnosis)
VALUES (1, 1, '2024-05-22 15:03:49.68703', false, 'Результаты входных/выходных анкет:
Результаты отсутствуют
Результаты промежуточных анкет:
Результаты отсутствуют
', 'Продолжение реабилитационных мероприятий', '', '');


--
-- TOC entry 3552 (class 0 OID 44646)
-- Dependencies: 248
-- Data for Name: user_role; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.user_role (_user_id, _role_id)
VALUES (1, 2);
INSERT INTO arm.user_role (_user_id, _role_id)
VALUES (1, 1);
INSERT INTO arm.user_role (_user_id, _role_id)
VALUES (2, 1);
INSERT INTO arm.user_role (_user_id, _role_id)
VALUES (2, 2);
INSERT INTO arm.user_role (_user_id, _role_id)
VALUES (3, 3);


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 216
-- Name: form_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.form_id_seq', 3, true);


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 251
-- Name: form_question_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.form_question_id_seq', 5, true);


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 242
-- Name: interpretation_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.interpretation_id_seq', 8, true);


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 232
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.question_id_seq', 5, true);


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 236
-- Name: scale_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.scale_id_seq', 3, true);


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 240
-- Name: variant_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.variant_id_seq', 12, true);


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 246
-- Name: _role_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm._role_id_seq', 1, false);


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 244
-- Name: _user_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm._user_id_seq', 1, false);


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 226
-- Name: block_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.block_id_seq', 1, false);


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 212
-- Name: doctor_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.doctor_id_seq', 1, false);


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 218
-- Name: exercise_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.exercise_id_seq', 1, false);


--
-- TOC entry 3581 (class 0 OID 0)
-- Dependencies: 238
-- Name: exercise_type_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.exercise_type_id_seq', 1, false);


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 249
-- Name: module_exercise_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.module_exercise_id_seq', 3, true);


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 259
-- Name: module_form_answer_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.module_form_answer_id_seq', 1, false);


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 257
-- Name: module_form_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.module_form_id_seq', 1, true);


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 224
-- Name: module_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.module_id_seq', 1, true);


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 230
-- Name: passport_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.passport_id_seq', 1, false);


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 214
-- Name: patient_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.patient_id_seq', 1, false);


--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 234
-- Name: program_form_answer_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.program_form_answer_id_seq', 1, false);


--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 253
-- Name: program_form_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.program_form_id_seq', 2, true);


--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 228
-- Name: protocol_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.protocol_id_seq', 1, true);


--
-- TOC entry 3591 (class 0 OID 0)
-- Dependencies: 222
-- Name: rehab_program_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.rehab_program_id_seq', 1, true);


--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 220
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.status_id_seq', 1, false);


--
-- TOC entry 3593 (class 0 OID 0)
-- Dependencies: 255
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: arm; Owner: postgres
--

SELECT pg_catalog.setval('arm.type_id_seq', 1, false);


-- Completed on 2024-05-22 18:11:09

--
-- PostgreSQL database dump complete
--

