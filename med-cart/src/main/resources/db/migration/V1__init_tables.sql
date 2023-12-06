-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.4
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file.
-- These commands were put in this file only as a convenience.
--
-- object: rc_medcarta | type: DATABASE --
-- DROP DATABASE IF EXISTS rc_medcarta;
-- CREATE DATABASE rc_medcarta;
-- ddl-end --


-- object: medcarta | type: SCHEMA --
-- DROP SCHEMA IF EXISTS medcarta CASCADE;
CREATE SCHEMA medcarta;
-- ddl-end --
ALTER SCHEMA medcarta OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,medcarta;
-- ddl-end --

-- object: medcarta.epicrisis | type: TABLE --
-- DROP TABLE IF EXISTS medcarta.epicrisis CASCADE;
CREATE TABLE medcarta.epicrisis
(
    id              bigserial NOT NULL,
    mu_code         bigint    NOT NULL,
    doctor_code     bigint    NOT NULL,
    diagnosis_code  bigint    NOT NULL,
    epicrisisi_data text      NOT NULL,
    creation_date   date      NOT NULL,
    patient_id      bigint    NOT NULL,
    CONSTRAINT epicrisis_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE medcarta.epicrisis
    OWNER TO postgres;
-- ddl-end --

-- object: medcarta.hospitalization_history | type: TABLE --
-- DROP TABLE IF EXISTS medcarta.hospitalization_history CASCADE;
CREATE TABLE medcarta.hospitalization_history
(
    id          bigserial NOT NULL,
    mu_code     bigint    NOT NULL,
    start_date  date      NOT NULL,
    end_date    date      NOT NULL,
    patient_id  bigint    NOT NULL,
    doctor_code bigint    NOT NULL,
    hosp_data   text      NOT NULL,
    CONSTRAINT hostpitalization_history_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE medcarta.hospitalization_history
    OWNER TO postgres;
-- ddl-end --

-- object: medcarta.patient | type: TABLE --
-- DROP TABLE IF EXISTS medcarta.patient CASCADE;
CREATE TABLE medcarta.patient
(
    id              bigserial    NOT NULL,
    patient_code    bigint       NOT NULL,
    first_name      varchar(255) NOT NULL,
    middle_name     varchar(255),
    last_name       varchar(255) NOT NULL,
    birth_date      date         NOT NULL,
    death_date      date,
    address         text         NOT NULL,
    phone_number    varchar(18)  NOT NULL,
    work_place_data text         NOT NULL,
    bookmark        text,
    snils           char(11)     NOT NULL,
    polis           char(16)     NOT NULL,
    passport_id     bigint       NOT NULL,
    CONSTRAINT patient_pk PRIMARY KEY (id),
    CONSTRAINT patient_code_uq UNIQUE (patient_code)
);
-- ddl-end --
ALTER TABLE medcarta.patient
    OWNER TO postgres;
-- ddl-end --

-- object: medcarta.passport | type: TABLE --
-- DROP TABLE IF EXISTS medcarta.passport CASCADE;
CREATE TABLE medcarta.passport
(
    id          bigserial NOT NULL,
    series      char(4)   NOT NULL,
    number      char(6)   NOT NULL,
    issued      text      NOT NULL,
    issued_date date      NOT NULL,
    CONSTRAINT passport_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE medcarta.passport
    OWNER TO postgres;
-- ddl-end --

-- object: passport_fk | type: CONSTRAINT --
-- ALTER TABLE medcarta.patient DROP CONSTRAINT IF EXISTS passport_fk CASCADE;
ALTER TABLE medcarta.patient
    ADD CONSTRAINT passport_fk FOREIGN KEY (passport_id)
        REFERENCES medcarta.passport (id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_uq | type: CONSTRAINT --
-- ALTER TABLE medcarta.patient DROP CONSTRAINT IF EXISTS patient_uq CASCADE;
ALTER TABLE medcarta.patient
    ADD CONSTRAINT patient_uq UNIQUE (passport_id);
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE medcarta.epicrisis DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE medcarta.epicrisis
    ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
        REFERENCES medcarta.patient (id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE medcarta.hospitalization_history DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE medcarta.hospitalization_history
    ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
        REFERENCES medcarta.patient (id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


