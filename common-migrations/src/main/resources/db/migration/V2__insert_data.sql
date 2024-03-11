--
-- TOC entry 3526 (class 0 OID 32440)
-- Dependencies: 237
-- Data for Name: scale; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.scale VALUES (1, 'Шкала A', 'Описание шкалы A');
INSERT INTO anketi.scale VALUES (2, 'Шкала B', 'Описание шкалы B');
INSERT INTO anketi.scale VALUES (3, 'Шкала C', 'Описание шкалы C');

--
-- TOC entry 3506 (class 0 OID 32327)
-- Dependencies: 217
-- Data for Name: form; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form VALUES (3, 3, 'Тестовая анкета 3', 'Описание');
INSERT INTO anketi.form VALUES (1, 1, 'Погода', 'Анкета с двумя вопросами');
INSERT INTO anketi.form VALUES (2, 2, 'Что Вы ели', 'Анкета с тремя вопросами');

--
-- TOC entry 3522 (class 0 OID 32424)
-- Dependencies: 233
-- Data for Name: question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.question VALUES (1, 'Как у Вас дела?');
INSERT INTO anketi.question VALUES (2, 'Какая сегодня погода?');
INSERT INTO anketi.question VALUES (3, 'Что Вы ели на завтрак?');
INSERT INTO anketi.question VALUES (4, 'Что Вы ели на обед?');
INSERT INTO anketi.question VALUES (5, 'Что Вы ели на ужин');

--
-- TOC entry 3545 (class 0 OID 32578)
-- Dependencies: 256
-- Data for Name: form_question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form_question VALUES (1, 1, 1, '2024-03-04 19:07:15');
INSERT INTO anketi.form_question VALUES (2, 2, 1, '2024-03-04 19:07:17');
INSERT INTO anketi.form_question VALUES (3, 3, 2, '2024-03-04 19:07:18');
INSERT INTO anketi.form_question VALUES (4, 4, 2, '2024-03-04 19:07:19');
INSERT INTO anketi.form_question VALUES (5, 5, 2, '2024-03-04 19:07:20');

--
-- TOC entry 3530 (class 0 OID 32468)
-- Dependencies: 241
-- Data for Name: variant; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.variant VALUES (6, 2, 1.50, 'Пасмурно');
INSERT INTO anketi.variant VALUES (4, 2, 4.00, 'Ясная');
INSERT INTO anketi.variant VALUES (3, 1, 2.50, 'Не очень');
INSERT INTO anketi.variant VALUES (5, 2, 2.00, 'Местами облачность');
INSERT INTO anketi.variant VALUES (2, 1, 3.00, 'Хорошо');
INSERT INTO anketi.variant VALUES (1, 1, 5.00, 'Отлично');
INSERT INTO anketi.variant VALUES (7, 3, 1.00, 'Кашу');
INSERT INTO anketi.variant VALUES (8, 3, 0.00, 'Ничего');
INSERT INTO anketi.variant VALUES (9, 4, 1.00, 'Суп');
INSERT INTO anketi.variant VALUES (10, 4, 0.00, 'Ничего');
INSERT INTO anketi.variant VALUES (11, 5, 1.00, 'Рис');
INSERT INTO anketi.variant VALUES (12, 5, 0.00, 'Ничего');

--
-- TOC entry 3538 (class 0 OID 32517)
-- Dependencies: 249
-- Data for Name: _role; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm._role VALUES (1, 'ADMIN');
INSERT INTO arm._role VALUES (2, 'DOCTOR');
INSERT INTO arm._role VALUES (3, 'PATIENT');

--
-- TOC entry 3536 (class 0 OID 32508)
-- Dependencies: 247
-- Data for Name: _user; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm._user VALUES (1, 'admin', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');
INSERT INTO arm._user VALUES (2, 'user', '$2a$12$Kwjwg2cajJef3S/8uCWPwuTg8PAXTDuJ9TPXZ66LVXpegbgSbvsGW');
INSERT INTO arm._user VALUES (3, 'patient', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');

--
-- TOC entry 3502 (class 0 OID 32305)
-- Dependencies: 213
-- Data for Name: doctor; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.doctor VALUES (1, 100, 1, 'Михаил', 'Алексеевич', 'Батухтин', 'giezz@vk.com', '777777');
INSERT INTO arm.doctor VALUES (2, 101, 2, 'Тест', 'Тестович', 'Тестов', 'test@vk.com', '777771');

--
-- TOC entry 3520 (class 0 OID 32408)
-- Dependencies: 231
-- Data for Name: passport; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.passport VALUES (1, '2345', '678901', '2012-08-20', 'Отделение УФМС по г. Санкт-Петербургу');
INSERT INTO arm.passport VALUES (2, '3456', '789012', '2014-02-10', 'Отделение УФМС по г. Екатеринбургу');
INSERT INTO arm.passport VALUES (3, '4567', '890123', '2007-11-05', 'Отделение УФМС по г. Нижний Новгороду');
INSERT INTO arm.passport VALUES (4, '5678', '901234', '2015-06-15', 'Отделение УФМС по г. Казани');
INSERT INTO arm.passport VALUES (5, '6789', '12345 ', '2018-03-25', 'Отделение УФМС по г. Владивостоку');

--
-- TOC entry 3510 (class 0 OID 32345)
-- Dependencies: 221
-- Data for Name: status; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.status VALUES (1, 'Нуждается в реабилитации');
INSERT INTO arm.status VALUES (2, 'Проходит реабилитацию');
INSERT INTO arm.status VALUES (3, 'Проходил реабилитацию ранее');

--
-- TOC entry 3504 (class 0 OID 32316)
-- Dependencies: 215
-- Data for Name: patient; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.patient VALUES (1, 1008, 2, 4, 3, 'Иван', 'Дмитриевич', 'Петров', '5554444', '56789012345', '6789012345678901', 'm', NULL, '1988-09-10', 'пр. Ленинградский, д. 25, кв. 7, Казань', NULL, 'Лукойл');
INSERT INTO arm.patient VALUES (2, 1009, 2, 5, 2, 'Мария', 'Андреевна', 'Новикова', '5555555', '67890123456', '7890123456789012', 'f', NULL, '1995-04-27', 'ул. Красная, д. 18, кв. 6, Владивосток', NULL, 'Сбербанк');
INSERT INTO arm.patient VALUES (3, 1006, 2, 2, 2, 'Андрей', 'Валерьевич', 'Сидоров', '5552222', '34567890123', '4567890123456789', 'm', NULL, '1980-12-05', 'пр. Победы, д. 15, кв. 3, Екатеринбург', NULL, 'Транснефть');
INSERT INTO arm.patient VALUES (4, 1005, 2, 1, 1, 'Елена', 'Петровна', 'Иванова', '5551111', '23456789012', '3456789012345678', 'f', NULL, '1992-03-18', 'ул. Ленина, д. 20, кв. 8, Санкт-Петербург', NULL, 'Газпром');
INSERT INTO arm.patient VALUES (5, 1007, 3, 3, 1, 'Ольга', 'Сергеевна', 'Кузнецова', '5553333', '45678901234', '5678901234567890', 'f', NULL, '1975-06-30', 'ул. Гагарина, д. 8, кв. 12, Нижний Новгород', NULL, 'РЖД');

--
-- TOC entry 3516 (class 0 OID 32375)
-- Dependencies: 227
-- Data for Name: block; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.block VALUES (1, 'Разминка');
INSERT INTO arm.block VALUES (2, 'Основной блок');
INSERT INTO arm.block VALUES (3, 'Заминка');

INSERT INTO arm.type VALUES (1, 'Вводная анкета');
INSERT INTO arm.type VALUES (2, 'Выходная анкета');

--
-- TOC entry 3528 (class 0 OID 32454)
-- Dependencies: 239
-- Data for Name: exercise_type; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.exercise_type VALUES (1, 'Тип упражнения 1');
INSERT INTO arm.exercise_type VALUES (2, 'Тип упражнения 2');
INSERT INTO arm.exercise_type VALUES (3, 'Тип упражнения 3');

--
-- TOC entry 3508 (class 0 OID 32336)
-- Dependencies: 219
-- Data for Name: exercise; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.exercise VALUES (1, 1, 'https://www.example.com/video1', 'Упражнение 1', 'Описание упражнения 1');
INSERT INTO arm.exercise VALUES (2, 2, 'https://www.example.com/video2', 'Упражнение 2', 'Описание упражнения 2');
INSERT INTO arm.exercise VALUES (3, 3, 'https://www.example.com/video3', 'Упражнение 3', 'Описание упражнения 3');

--
-- TOC entry 3539 (class 0 OID 32528)
-- Dependencies: 250
-- Data for Name: user_role; Type: TABLE DATA; Schema: arm; Owner: postgres
--

INSERT INTO arm.user_role VALUES (1, 2);
INSERT INTO arm.user_role VALUES (1, 1);
INSERT INTO arm.user_role VALUES (2, 1);
INSERT INTO arm.user_role VALUES (2, 2);
INSERT INTO arm.user_role VALUES (3, 3);
