INSERT INTO doc._user (id, login, password) VALUES (1, 'admin', '$2a$12$XM6RePLH/FcCB1yu3izFYOQRLaiYr/u8F2NrmJwBH/KJbpct1eJQu');
INSERT INTO doc._user (id, login, password) VALUES (2, 'user', '$2a$12$Kwjwg2cajJef3S/8uCWPwuTg8PAXTDuJ9TPXZ66LVXpegbgSbvsGW');

INSERT INTO doc._role (id, name) VALUES (1, 'ADMIN');
INSERT INTO doc._role (id, name) VALUES (2, 'DOCTOR');

INSERT INTO doc.user_role (_user_id, _role_id) VALUES (1, 2);
INSERT INTO doc.user_role (_user_id, _role_id) VALUES (1, 1);

INSERT INTO doc.doctor (id, doctor_code, first_name, middle_name, last_name, email, phone_number, _user_id) VALUES (1, 100, 'Михаил', 'Алексеевич', 'Батухтин', 'giezz@vk.com', '777777', 1);

INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (1, '2345', '678901', 'Отделение УФМС по г. Санкт-Петербургу', '2012-08-20');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (2, '3456', '789012', 'Отделение УФМС по г. Екатеринбургу', '2014-02-10');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (3, '4567', '890123', 'Отделение УФМС по г. Нижний Новгороду', '2007-11-05');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (4, '5678', '901234', 'Отделение УФМС по г. Казани', '2015-06-15');
INSERT INTO doc.passport (id, series, number, issued, issued_date) VALUES (5, '6789', '12345 ', 'Отделение УФМС по г. Владивостоку', '2018-03-25');

INSERT INTO doc.patient_status (id, name) VALUES (1, 'Нуждается в реабилитации');
INSERT INTO doc.patient_status (id, name) VALUES (2, 'Проходит реабилитацию');
INSERT INTO doc.patient_status (id, name) VALUES (3, 'Проходил реабилитацию ранее');

INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id) VALUES (1, 1005, 'Елена', 'Петровна', 'Иванова', '1992-03-18', null, 'ул. Ленина, д. 20, кв. 8, Санкт-Петербург', '5551111', 'Газпром', null, '23456789012', '3456789012345678', 1, 1);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id) VALUES (2, 1006, 'Андрей', 'Валерьевич', 'Сидоров', '1980-12-05', null, 'пр. Победы, д. 15, кв. 3, Екатеринбург', '5552222', 'Транснефть', null, '34567890123', '4567890123456789', 2, 2);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id) VALUES (3, 1007, 'Ольга', 'Сергеевна', 'Кузнецова', '1975-06-30', null, 'ул. Гагарина, д. 8, кв. 12, Нижний Новгород', '5553333', 'РЖД', null, '45678901234', '5678901234567890', 1, 3);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id) VALUES (4, 1008, 'Иван', 'Дмитриевич', 'Петров', '1988-09-10', null, 'пр. Ленинградский, д. 25, кв. 7, Казань', '5554444', 'Лукойл', null, '56789012345', '6789012345678901', 3, 4);
INSERT INTO doc.patient (id, patient_code, first_name, middle_name, last_name, birth_date, death_date, address, phone_number, work_place_data, bookmark, snils, polis, patient_status_id, passport_id) VALUES (5, 1009, 'Мария', 'Андреевна', 'Новикова', '1995-04-27', null, 'ул. Красная, д. 18, кв. 6, Владивосток', '5555555', 'Сбербанк', null, '67890123456', '7890123456789012', 2, 5);
