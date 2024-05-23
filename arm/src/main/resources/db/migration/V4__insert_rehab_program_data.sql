-- DATA for Name: rehab_program; Type: TABLE DATA; Schema: arm; Owner: postgres
INSERT INTO arm.rehab_program (doctor_id, patient_id, is_current, created_at, start_date, end_date) VALUES (1, 1, true, '2023-01-01 10:00:00', '2023-01-02 10:00:00', NULL);
INSERT INTO arm.rehab_program (doctor_id, patient_id, is_current, created_at, start_date, end_date) VALUES (1, 3, true, '2023-01-01 10:00:00', '2023-01-02 10:00:00', NULL);
INSERT INTO arm.rehab_program (doctor_id, patient_id, is_current, created_at, start_date, end_date) VALUES (2, 2, false, '2023-02-01 11:00:00', '2023-02-02 11:00:00', '2023-07-01 11:00:00');
INSERT INTO arm.rehab_program (doctor_id, patient_id, is_current, created_at, start_date, end_date) VALUES (2, 3, false, '2023-02-01 11:00:00', '2023-02-02 11:00:00', '2023-07-01 11:00:00');
INSERT INTO arm.rehab_program (doctor_id, patient_id, is_current, created_at, start_date, end_date) VALUES (2, 5, true, '2023-02-01 11:00:00', '2023-02-02 11:00:00', NULL);

-- DATA for Name: module; Type: TABLE DATA; Schema: arm; Owner: postgres
INSERT INTO arm.module (rehab_program_id, finished_at, name) VALUES (1, '2023-03-01 12:00:00', 'Module 1');
INSERT INTO arm.module (rehab_program_id, finished_at, name) VALUES (2, '2023-04-01 13:00:00', 'Module 2');
INSERT INTO arm.module (rehab_program_id, finished_at, name) VALUES (4, '2023-03-01 12:00:00', 'Module 1');
INSERT INTO arm.module (rehab_program_id, finished_at, name) VALUES (4, '2023-04-01 13:00:00', 'Module 2');

-- DATA for Name: module_exercise; Type: TABLE DATA; Schema: arm; Owner: postgres
INSERT INTO arm.protocol (rehab_program_id, creation_date, is_final, scales_result, rehab_result, recommendations, rehab_diagnosis) VALUES (1, '2023-05-01 14:00:00', false, 'Scale 1 Result', 'Rehab 1 Result', 'Recommendation 1', 'Diagnosis 1');
INSERT INTO arm.protocol (rehab_program_id, creation_date, is_final, scales_result, rehab_result, recommendations, rehab_diagnosis) VALUES (2, '2023-06-01 15:00:00', true, 'Scale 2 Result', 'Rehab 2 Result', 'Recommendation 2', 'Diagnosis 2');
INSERT INTO arm.protocol (rehab_program_id, creation_date, is_final, scales_result, rehab_result, recommendations, rehab_diagnosis) VALUES (4, '2023-06-01 15:00:00', true, 'Scale 2 Result', 'Rehab 2 Result', 'Recommendation 2', 'Diagnosis 2');
INSERT INTO arm.protocol (rehab_program_id, creation_date, is_final, scales_result, rehab_result, recommendations, rehab_diagnosis) VALUES (4, '2023-06-01 15:00:00', false, 'Scale 2 Result', 'Rehab 2 Result', 'Recommendation 2', 'Diagnosis 2');

INSERT INTO arm.module_exercise (exercise_id, module_id, block_id, finished_at) VALUES (1, 1, 1, '2023-07-01 16:00:00');
INSERT INTO arm.module_exercise (exercise_id, module_id, block_id, finished_at) VALUES (1, 3, 1, '2023-07-01 16:00:00');
INSERT INTO arm.module_exercise (exercise_id, module_id, block_id, finished_at) VALUES (2, 3, 2, '2023-07-01 16:00:00');
INSERT INTO arm.module_exercise (exercise_id, module_id, block_id, finished_at) VALUES (3, 4, 2, '2023-07-01 16:00:00');
INSERT INTO arm.module_exercise (exercise_id, module_id, block_id, finished_at) VALUES (2, 2, 2, '2023-08-01 17:00:00');


INSERT INTO arm.program_form (rehab_program_id, form_id, type_id, finished_at, score) VALUES (1, 1, 1, '2023-11-01 20:00:00', 9);
INSERT INTO arm.program_form (rehab_program_id, form_id, type_id, finished_at, score) VALUES (1, 1, 1, '2023-11-01 20:00:00', 9);
INSERT INTO arm.program_form (rehab_program_id, form_id, type_id, finished_at, score) VALUES (4, 3, 1, '2023-12-01 21:00:00', 9);
INSERT INTO arm.program_form (rehab_program_id, form_id, type_id, finished_at, score) VALUES (4, 3, 2, '2023-12-01 21:00:00', 9);
INSERT INTO arm.program_form (rehab_program_id, form_id, type_id, finished_at, score) VALUES (2, 2, 2, '2023-12-01 21:00:00', 9);

INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (1, 1);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (3, 1);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (1, 2);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (3, 2);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (7, 3);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (9, 3);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (7, 4);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (9, 4);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (7, 5);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (9, 5);
INSERT INTO arm.program_form_answer (variant_id, program_form_id) VALUES (11, 5);


INSERT INTO arm.module_form (module_id, form_id, finished_at, score) VALUES (1, 1, '2024-01-01 22:00:00', 90.0);
INSERT INTO arm.module_form (module_id, form_id, finished_at, score) VALUES (3, 1, '2023-07-01 16:00:00', 70.0);
INSERT INTO arm.module_form (module_id, form_id, finished_at, score) VALUES (3, 2, '2023-07-01 16:00:00', 70.0);
INSERT INTO arm.module_form (module_id, form_id, finished_at, score) VALUES (3, 3, '2023-07-01 16:00:00', 70.0);
INSERT INTO arm.module_form (module_id, form_id, finished_at, score) VALUES (2, 2, '2024-02-01 23:00:00', 80.0);

INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (1, 1);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (1, 2);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (2, 1);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (2, 2);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (3, 1);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (3, 2);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (4, 1);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (4, 2);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (5, 1);
INSERT INTO arm.module_form_answer (module_form_id, variant_id) VALUES (5, 2);