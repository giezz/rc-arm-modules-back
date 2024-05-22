--
-- TOC entry 3526 (class 0 OID 32440)
-- Dependencies: 237
-- Data for Name: scale; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.scale(name, description) VALUES ('Шкала A', 'Описание шкалы A');
INSERT INTO anketi.scale(name, description) VALUES ('Шкала B', 'Описание шкалы B');
INSERT INTO anketi.scale(name, description) VALUES ('Шкала C', 'Описание шкалы C');

--
-- TOC entry 3506 (class 0 OID 32327)
-- Dependencies: 217
-- Data for Name: form; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form(scale_id, name, description) VALUES (3, 'Тестовая анкета 3', 'Описание');
INSERT INTO anketi.form(scale_id, name, description) VALUES (1, 'Погода', 'Анкета с двумя вопросами');
INSERT INTO anketi.form(scale_id, name, description) VALUES (2, 'Что Вы ели', 'Анкета с тремя вопросами');

--
-- TOC entry 3522 (class 0 OID 32424)
-- Dependencies: 233
-- Data for Name: question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.question(content, type) VALUES ('Как у Вас дела?', 'SINGLE_CHOICE');
INSERT INTO anketi.question(content, type) VALUES ('Какая сегодня погода?', 'MULTIPLE_CHOICE');
INSERT INTO anketi.question(content, type) VALUES ('Что Вы ели на завтрак?', 'SINGLE_CHOICE');
INSERT INTO anketi.question(content, type) VALUES ('Что Вы ели на обед?', 'SINGLE_CHOICE');
INSERT INTO anketi.question(content, type) VALUES ('Что Вы ели на ужин', 'SINGLE_CHOICE');

--
-- TOC entry 3545 (class 0 OID 32578)
-- Dependencies: 256
-- Data for Name: form_question; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.form_question(question_id, form_id, created_at) VALUES (1, 1, '2024-03-04 19:07:15');
INSERT INTO anketi.form_question(question_id, form_id, created_at) VALUES (2, 1, '2024-03-04 19:07:17');
INSERT INTO anketi.form_question(question_id, form_id, created_at) VALUES (3, 2, '2024-03-04 19:07:18');
INSERT INTO anketi.form_question(question_id, form_id, created_at) VALUES (4, 2, '2024-03-04 19:07:19');
INSERT INTO anketi.form_question(question_id, form_id, created_at) VALUES (5, 2, '2024-03-04 19:07:20');

--
-- TOC entry 3530 (class 0 OID 32468)
-- Dependencies: 241
-- Data for Name: variant; Type: TABLE DATA; Schema: anketi; Owner: postgres
--

INSERT INTO anketi.variant(question_id, score, content) VALUES (2, 1.50, 'Пасмурно');
INSERT INTO anketi.variant(question_id, score, content) VALUES (2, 4.00, 'Ясная');
INSERT INTO anketi.variant(question_id, score, content) VALUES (1, 2.50, 'Не очень');
INSERT INTO anketi.variant(question_id, score, content) VALUES (2, 2.00, 'Местами облачность');
INSERT INTO anketi.variant(question_id, score, content) VALUES (1, 3.00, 'Хорошо');
INSERT INTO anketi.variant(question_id, score, content) VALUES (1, 5.00, 'Отлично');
INSERT INTO anketi.variant(question_id, score, content) VALUES (3, 1.00, 'Кашу');
INSERT INTO anketi.variant(question_id, score, content) VALUES (3, 0.00, 'Ничего');
INSERT INTO anketi.variant(question_id, score, content) VALUES (4, 1.00, 'Суп');
INSERT INTO anketi.variant(question_id, score, content) VALUES (4, 0.00, 'Ничего');
INSERT INTO anketi.variant(question_id, score, content) VALUES (5, 1.00, 'Рис');
INSERT INTO anketi.variant(question_id, score, content) VALUES (5, 0.00, 'Ничего');


INSERT INTO anketi.interpretation(scale_id, min_value, max_value, description) VALUES (1, 1, 5, 'Отлично');
INSERT INTO anketi.interpretation(scale_id, min_value, max_value, description) VALUES (1, 6, 10, 'Хорошо');
INSERT INTO anketi.interpretation(scale_id, min_value, max_value, description) VALUES (1, 11, 15, 'Отлично');
INSERT INTO anketi.interpretation(scale_id, min_value, max_value, description) VALUES (1, 16, 20, 'Кашу');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description) VALUES (DEFAULT, 2, 0.00, 5.00, 'scale B. BAD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description) VALUES (DEFAULT, 2, 6.00, 10.00, 'scale B. GOOD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description) VALUES (DEFAULT, 3, 0.00, 5.00, 'scale C. BAD');
INSERT INTO anketi.interpretation (id, scale_id, min_value, max_value, description) VALUES (DEFAULT, 3, 6.00, 10.00, 'scale C. GOOD');