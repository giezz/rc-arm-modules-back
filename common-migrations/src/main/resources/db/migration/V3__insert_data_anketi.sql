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

INSERT INTO anketi.question VALUES (1, 'Как у Вас дела?', 'SINGLE_CHOICE');
INSERT INTO anketi.question VALUES (2, 'Какая сегодня погода?', 'MULTIPLE_CHOICE');
INSERT INTO anketi.question VALUES (3, 'Что Вы ели на завтрак?', 'SINGLE_CHOICE');
INSERT INTO anketi.question VALUES (4, 'Что Вы ели на обед?', 'SINGLE_CHOICE');
INSERT INTO anketi.question VALUES (5, 'Что Вы ели на ужин', 'SINGLE_CHOICE');

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
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 216
-- Name: form_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.form_id_seq', 1, false);


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 251
-- Name: form_question_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.form_question_id_seq', 1, false);


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 242
-- Name: interpretation_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.interpretation_id_seq', 1, false);


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 232
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.question_id_seq', 1, false);


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 236
-- Name: scale_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.scale_id_seq', 1, false);


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 240
-- Name: variant_id_seq; Type: SEQUENCE SET; Schema: anketi; Owner: postgres
--

SELECT pg_catalog.setval('anketi.variant_id_seq', 1, false);