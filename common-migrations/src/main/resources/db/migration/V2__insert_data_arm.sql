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
INSERT INTO arm.passport VALUES (6, '7890', '23456', '2013-09-30', 'Отделение УФМС по г. Самаре');
INSERT INTO arm.passport VALUES (7, '8901', '34567', '2016-12-10', 'Отделение УФМС по г. Ростову-на-Дону');
INSERT INTO arm.passport VALUES (8, '9012', '45678', '2009-04-18', 'Отделение УФМС по г. Уфе');
INSERT INTO arm.passport VALUES (9, '0123', '56789', '2017-07-22', 'Отделение УФМС по г. Краснодару');
INSERT INTO arm.passport VALUES (10, '1234', '67890', '2011-10-05', 'Отделение УФМС по г. Челябинску');
INSERT INTO arm.passport VALUES (11, '2345', '78901', '2019-05-15', 'Отделение УФМС по г. Омску');
INSERT INTO arm.passport VALUES (12, '3456', '89012', '2008-08-28', 'Отделение УФМС по г. Тюмени');
INSERT INTO arm.passport VALUES (13, '4567', '90123', '2012-11-20', 'Отделение УФМС по г. Иркутску');
INSERT INTO arm.passport VALUES (14, '5678', '01234', '2015-04-03', 'Отделение УФМС по г. Хабаровску');
INSERT INTO arm.passport VALUES (15, '6789', '12345', '2010-07-08', 'Отделение УФМС по г. Волгограду');


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

INSERT INTO arm.patient VALUES (1, 1008, 2, 4, 1, 'Иван', 'Дмитриевич', 'Петров', '5554444', '56789012345', '6789012345678901', 'm', NULL, '1988-09-10', 'пр. Ленинградский, д. 25, кв. 7, Казань', NULL, 'Лукойл');
INSERT INTO arm.patient VALUES (2, 1009, 2, 5, 1, 'Мария', 'Андреевна', 'Новикова', '5555555', '67890123456', '7890123456789012', 'f', NULL, '1995-04-27', 'ул. Красная, д. 18, кв. 6, Владивосток', NULL, 'Сбербанк');
INSERT INTO arm.patient VALUES (3, 1006, 2, 2, 1, 'Андрей', 'Валерьевич', 'Сидоров', '5552222', '34567890123', '4567890123456789', 'm', NULL, '1980-12-05', 'пр. Победы, д. 15, кв. 3, Екатеринбург', NULL, 'Транснефть');
INSERT INTO arm.patient VALUES (4, 1005, 2, 1, 1, 'Елена', 'Петровна', 'Иванова', '5551111', '23456789012', '3456789012345678', 'f', NULL, '1992-03-18', 'ул. Ленина, д. 20, кв. 8, Санкт-Петербург', NULL, 'Газпром');
INSERT INTO arm.patient VALUES (5, 1007, 3, 3, 1, 'Ольга', 'Сергеевна', 'Кузнецова', '5553333', '45678901234', '5678901234567890', 'f', NULL, '1975-06-30', 'ул. Гагарина, д. 8, кв. 12, Нижний Новгород', NULL, 'РЖД');
INSERT INTO arm.patient VALUES (6, 1010, 2, 6, 1, 'Тест', 'Тестович', 'Тестов', '5553331', '45678901231', '5678901234567891', 'f', NULL, '1975-06-30', 'Тестовый адрес', NULL, 'Тестовая работа');
INSERT INTO arm.patient VALUES (7, 1011, 2, 7, 1, 'Иван', 'Иванович', 'Иванов', '5553332', '45678901232', '5678901234567892', 'm', NULL, '1982-09-15', 'Улица Пушкина, дом Колотушкина', NULL, 'Менеджер');
INSERT INTO arm.patient VALUES (8, 1012, 2, 8, 1, 'Мария', 'Алексеевна', 'Сидорова', '5553333', '45678901233', '5678901234567893', 'f', NULL, '1990-03-22', 'Проезд Орлова, дом Петрова', NULL, 'Бухгалтер');
INSERT INTO arm.patient VALUES (9, 1013, 2, 9, 1, 'Петр', 'Семенович', 'Петров', '5553334', '45678901234', '5678901234567894', 'm', NULL, '1978-12-05', 'Проспект Ленина, дом Сталина', NULL, 'Инженер');
INSERT INTO arm.patient VALUES (10, 1014, 2, 10, 1, 'Елена', 'Васильевна', 'Сергеева', '5553335', '45678901235', '5678901234567895', 'f', NULL, '1985-05-20', 'Бульвар Гагарина, дом Ленина', NULL, 'Учитель');
INSERT INTO arm.patient VALUES (11, 1015, 2, 11, 1, 'Александр', 'Павлович', 'Кузнецов', '5553336', '45678901266', '5678901234567896', 'm', NULL, '1980-08-10', 'Улица Лермонтова, дом Пушкина', NULL, 'Врач');
INSERT INTO arm.patient VALUES (12, 1016, 2, 12, 1, 'Наталья', 'Дмитриевна', 'Васильева', '5553337', '45678901237', '5678901234567897', 'f', NULL, '1992-01-25', 'Переулок Тургенева, дом Чехова', NULL, 'Художник');
INSERT INTO arm.patient VALUES (13, 1017, 2, 13, 1, 'Дмитрий', 'Александрович', 'Козлов', '5553338', '45678901238', '2345678901234567', 'm', NULL, '1970-04-18', 'Проезд Пушкина, дом Гоголя', NULL, 'Администратор');
INSERT INTO arm.patient VALUES (14, 1018, 2, 14, 1, 'Анна', 'Сергеевна', 'Иванова', '5553339', '45678901239', '3456789012345678', 'f', NULL, '1988-07-12', 'Улица Горького, дом Достоевского', NULL, 'Менеджер по продажам');
INSERT INTO arm.patient VALUES (15, 1019, 2, 15, 1, 'Сергей', 'Николаевич', 'Смирнов', '5553340', '45678901230', '4567890123456789', 'm', NULL, '1977-10-30', 'Бульвар Ленина, дом Карла Маркса', NULL, 'Инженер-программист');

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
