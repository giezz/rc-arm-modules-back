-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.4
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file.
-- These commands were put in this file only as a convenience.
--
-- object: rc_doc | type: DATABASE --
-- DROP DATABASE IF EXISTS rc_doc;
-- CREATE DATABASE rc_doc;
-- ddl-end --


-- object: arm | type: SCHEMA --
-- DROP SCHEMA IF EXISTS arm CASCADE;
CREATE SCHEMA arm;
-- ddl-end --
ALTER SCHEMA arm OWNER TO postgres;
-- ddl-end --

-- object: anketi | type: SCHEMA --
-- DROP SCHEMA IF EXISTS anketi CASCADE;
CREATE SCHEMA anketi;
-- ddl-end --
ALTER SCHEMA anketi OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,arm,anketi;
-- ddl-end --

-- object: arm.doctor | type: TABLE --
-- DROP TABLE IF EXISTS arm.doctor CASCADE;
CREATE TABLE arm.doctor (
                            id bigserial NOT NULL,
                            doctor_code bigint NOT NULL,
                            _user_id bigint NOT NULL,
                            first_name varchar(255) NOT NULL,
                            middle_name varchar(255),
                            last_name varchar(255) NOT NULL,
                            email varchar(255) NOT NULL,
                            phone_number varchar(18) NOT NULL,
                            CONSTRAINT doctor_pk PRIMARY KEY (id),
                            CONSTRAINT doctor_code_uq UNIQUE (doctor_code)
);
-- ddl-end --
ALTER TABLE arm.doctor OWNER TO postgres;
-- ddl-end --

-- object: arm.patient | type: TABLE --
-- DROP TABLE IF EXISTS arm.patient CASCADE;
CREATE TABLE arm.patient (
                             id bigserial NOT NULL,
                             patient_code bigint NOT NULL,
                             _user_id bigint NOT NULL,
                             passport_id bigint NOT NULL,
                             status_id bigint NOT NULL,
                             first_name varchar(255) NOT NULL,
                             middle_name varchar(255),
                             last_name varchar(255) NOT NULL,
                             phone_number varchar(18) NOT NULL,
                             snils char(11) NOT NULL,
                             polis char(16) NOT NULL,
                             gender char(1) NOT NULL,
                             death_date date,
                             birth_date date NOT NULL,
                             address text NOT NULL,
                             bookmark text,
                             work_place_data text NOT NULL,
                             CONSTRAINT patient_pk PRIMARY KEY (id),
                             CONSTRAINT patient_code_uq UNIQUE (patient_code)
);
-- ddl-end --
ALTER TABLE arm.patient OWNER TO postgres;
-- ddl-end --

-- object: anketi.form | type: TABLE --
-- DROP TABLE IF EXISTS anketi.form CASCADE;
CREATE TABLE anketi.form (
                             id bigserial NOT NULL,
                             scale_id bigint,
                             name text NOT NULL,
                             description text,
                             CONSTRAINT form_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.form OWNER TO postgres;
-- ddl-end --

-- object: arm.exercise | type: TABLE --
-- DROP TABLE IF EXISTS arm.exercise CASCADE;
CREATE TABLE arm.exercise (
                              id bigserial NOT NULL,
                              exercise_type_id bigint,
                              video_url varchar(2083) NOT NULL,
                              name text NOT NULL,
                              description text,
                              CONSTRAINT exercise_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.exercise OWNER TO postgres;
-- ddl-end --

-- object: arm.status | type: TABLE --
-- DROP TABLE IF EXISTS arm.status CASCADE;
CREATE TABLE arm.status (
                            id bigserial NOT NULL,
                            name text NOT NULL,
                            CONSTRAINT patient_status_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.status OWNER TO postgres;
-- ddl-end --

-- object: status_fk | type: CONSTRAINT --
-- ALTER TABLE arm.patient DROP CONSTRAINT IF EXISTS status_fk CASCADE;
ALTER TABLE arm.patient ADD CONSTRAINT status_fk FOREIGN KEY (status_id)
    REFERENCES arm.status (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.rehab_program | type: TABLE --
-- DROP TABLE IF EXISTS arm.rehab_program CASCADE;
CREATE TABLE arm.rehab_program (
                                   id bigserial NOT NULL,
                                   doctor_id bigint NOT NULL,
                                   patient_id bigint NOT NULL,
                                   is_current boolean NOT NULL,
                                   created_at timestamp NOT NULL,
                                   start_date timestamp,
                                   end_date timestamp,
                                   CONSTRAINT rehabilitation_program_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.rehab_program OWNER TO postgres;
-- ddl-end --

-- object: arm.module | type: TABLE --
-- DROP TABLE IF EXISTS arm.module CASCADE;
CREATE TABLE arm.module (
                            id bigserial NOT NULL,
                            rehab_program_id bigint NOT NULL,
                            finished_at timestamp,
                            name text NOT NULL,
                            CONSTRAINT module_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.module OWNER TO postgres;
-- ddl-end --

-- object: arm.block | type: TABLE --
-- DROP TABLE IF EXISTS arm.block CASCADE;
CREATE TABLE arm.block (
                           id bigserial NOT NULL,
                           name text NOT NULL,
                           CONSTRAINT block_type_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.block OWNER TO postgres;
-- ddl-end --

-- object: arm.protocol | type: TABLE --
-- DROP TABLE IF EXISTS arm.protocol CASCADE;
CREATE TABLE arm.protocol (
                              id bigserial NOT NULL,
                              rehab_program_id bigint NOT NULL,
                              creation_date timestamp NOT NULL,
                              is_final boolean NOT NULL,
                              scales_result text NOT NULL,
                              rehab_result text NOT NULL,
                              recommendations text NOT NULL,
                              rehab_diagnosis text NOT NULL,
                              CONSTRAINT protocol_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.protocol OWNER TO postgres;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE arm.protocol DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE arm.protocol ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES arm.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.passport | type: TABLE --
-- DROP TABLE IF EXISTS arm.passport CASCADE;
CREATE TABLE arm.passport (
                              id bigserial NOT NULL,
                              series char(4) NOT NULL,
                              number char(6) NOT NULL,
                              issued_date date NOT NULL,
                              issued text NOT NULL,
                              CONSTRAINT passport_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.passport OWNER TO postgres;
-- ddl-end --

-- object: passport_fk | type: CONSTRAINT --
-- ALTER TABLE arm.patient DROP CONSTRAINT IF EXISTS passport_fk CASCADE;
ALTER TABLE arm.patient ADD CONSTRAINT passport_fk FOREIGN KEY (passport_id)
    REFERENCES arm.passport (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: passport_uq | type: CONSTRAINT --
-- ALTER TABLE arm.patient DROP CONSTRAINT IF EXISTS passport_uq CASCADE;
ALTER TABLE arm.patient ADD CONSTRAINT passport_uq UNIQUE (passport_id);
-- ddl-end --

-- object: anketi.question | type: TABLE --
-- DROP TABLE IF EXISTS anketi.question CASCADE;
CREATE TABLE anketi.question (
                                 id bigserial NOT NULL,
                                 content text NOT NULL,
                                 required boolean NOT NULL DEFAULT false,
                                 type text NOT NULL,
                                 CONSTRAINT question_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.question OWNER TO postgres;
-- ddl-end --

-- object: arm.program_form_answer | type: TABLE --
-- DROP TABLE IF EXISTS arm.program_form_answer CASCADE;
CREATE TABLE arm.program_form_answer (
                                         id bigserial NOT NULL,
                                         variant_id bigint,
                                         program_form_id bigint NOT NULL,
                                         CONSTRAINT answer_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.program_form_answer OWNER TO postgres;
-- ddl-end --

-- object: anketi.scale | type: TABLE --
-- DROP TABLE IF EXISTS anketi.scale CASCADE;
CREATE TABLE anketi.scale (
                              id bigserial NOT NULL,
                              name text NOT NULL,
                              description text,
                              CONSTRAINT scale_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.scale OWNER TO postgres;
-- ddl-end --

-- object: scale_fk | type: CONSTRAINT --
-- ALTER TABLE anketi.form DROP CONSTRAINT IF EXISTS scale_fk CASCADE;
ALTER TABLE anketi.form ADD CONSTRAINT scale_fk FOREIGN KEY (scale_id)
    REFERENCES anketi.scale (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.exercise_type | type: TABLE --
-- DROP TABLE IF EXISTS arm.exercise_type CASCADE;
CREATE TABLE arm.exercise_type (
                                   id bigserial NOT NULL,
                                   name text NOT NULL,
                                   CONSTRAINT exercise_type_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.exercise_type OWNER TO postgres;
-- ddl-end --

-- object: exercise_type_fk | type: CONSTRAINT --
-- ALTER TABLE arm.exercise DROP CONSTRAINT IF EXISTS exercise_type_fk CASCADE;
ALTER TABLE arm.exercise ADD CONSTRAINT exercise_type_fk FOREIGN KEY (exercise_type_id)
    REFERENCES arm.exercise_type (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: anketi.variant | type: TABLE --
-- DROP TABLE IF EXISTS anketi.variant CASCADE;
CREATE TABLE anketi.variant (
                                id bigserial NOT NULL,
                                question_id bigint NOT NULL,
                                score numeric(100,2) NOT NULL,
                                content text NOT NULL,
                                CONSTRAINT answer_variant_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.variant OWNER TO postgres;
-- ddl-end --

-- object: question_fk | type: CONSTRAINT --
-- ALTER TABLE anketi.variant DROP CONSTRAINT IF EXISTS question_fk CASCADE;
ALTER TABLE anketi.variant ADD CONSTRAINT question_fk FOREIGN KEY (question_id)
    REFERENCES anketi.question (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: variant_fk | type: CONSTRAINT --
-- ALTER TABLE arm.program_form_answer DROP CONSTRAINT IF EXISTS variant_fk CASCADE;
ALTER TABLE arm.program_form_answer ADD CONSTRAINT variant_fk FOREIGN KEY (variant_id)
    REFERENCES anketi.variant (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: anketi.interpretation | type: TABLE --
-- DROP TABLE IF EXISTS anketi.interpretation CASCADE;
CREATE TABLE anketi.interpretation (
                                       id bigserial NOT NULL,
                                       scale_id bigint NOT NULL,
                                       min_value numeric(100,2) NOT NULL,
                                       max_value numeric(100,2) NOT NULL,
                                       description text NOT NULL,
                                       CONSTRAINT interpretation_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.interpretation OWNER TO postgres;
-- ddl-end --

-- object: scale_fk | type: CONSTRAINT --
-- ALTER TABLE anketi.interpretation DROP CONSTRAINT IF EXISTS scale_fk CASCADE;
ALTER TABLE anketi.interpretation ADD CONSTRAINT scale_fk FOREIGN KEY (scale_id)
    REFERENCES anketi.scale (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm._user | type: TABLE --
-- DROP TABLE IF EXISTS arm._user CASCADE;
CREATE TABLE arm._user (
                           id bigserial NOT NULL,
                           login varchar(255) NOT NULL,
                           password varchar(255) NOT NULL,
                           CONSTRAINT _user_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm._user OWNER TO postgres;
-- ddl-end --

-- object: arm._role | type: TABLE --
-- DROP TABLE IF EXISTS arm._role CASCADE;
CREATE TABLE arm._role (
                           id bigserial NOT NULL,
                           name varchar(255) NOT NULL,
                           CONSTRAINT _role_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm._role OWNER TO postgres;
-- ddl-end --

-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE arm.doctor DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE arm.doctor ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES arm._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.user_role | type: TABLE --
-- DROP TABLE IF EXISTS arm.user_role CASCADE;
CREATE TABLE arm.user_role (
                               _user_id bigint NOT NULL,
                               _role_id bigint NOT NULL,
                               CONSTRAINT user_role_pk PRIMARY KEY (_user_id,_role_id)
);
-- ddl-end --

-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE arm.user_role DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE arm.user_role ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES arm._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: _role_fk | type: CONSTRAINT --
-- ALTER TABLE arm.user_role DROP CONSTRAINT IF EXISTS _role_fk CASCADE;
ALTER TABLE arm.user_role ADD CONSTRAINT _role_fk FOREIGN KEY (_role_id)
    REFERENCES arm._role (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE arm.module ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES arm.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE arm.patient DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE arm.patient ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES arm._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.module_exercise | type: TABLE --
-- DROP TABLE IF EXISTS arm.module_exercise CASCADE;
CREATE TABLE arm.module_exercise (
                                     id bigserial NOT NULL,
                                     exercise_id bigint NOT NULL,
                                     module_id bigint NOT NULL,
                                     block_id bigint NOT NULL,
                                     finished_at timestamp,
                                     CONSTRAINT module_exercise_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.module_exercise OWNER TO postgres;
-- ddl-end --

-- object: exercise_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_exercise DROP CONSTRAINT IF EXISTS exercise_fk CASCADE;
ALTER TABLE arm.module_exercise ADD CONSTRAINT exercise_fk FOREIGN KEY (exercise_id)
    REFERENCES arm.exercise (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: anketi.form_question | type: TABLE --
-- DROP TABLE IF EXISTS anketi.form_question CASCADE;
CREATE TABLE anketi.form_question (
                                      id bigserial NOT NULL,
                                      question_id bigint NOT NULL,
                                      form_id bigint NOT NULL,
                                      created_at timestamp,
                                      CONSTRAINT form_question_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE anketi.form_question OWNER TO postgres;
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE anketi.form_question DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE anketi.form_question ADD CONSTRAINT form_fk FOREIGN KEY (form_id)
    REFERENCES anketi.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: question_fk | type: CONSTRAINT --
-- ALTER TABLE anketi.form_question DROP CONSTRAINT IF EXISTS question_fk CASCADE;
ALTER TABLE anketi.form_question ADD CONSTRAINT question_fk FOREIGN KEY (question_id)
    REFERENCES anketi.question (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_exercise DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE arm.module_exercise ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES arm.module (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: block_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_exercise DROP CONSTRAINT IF EXISTS block_fk CASCADE;
ALTER TABLE arm.module_exercise ADD CONSTRAINT block_fk FOREIGN KEY (block_id)
    REFERENCES arm.block (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE arm.rehab_program DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE arm.rehab_program ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
    REFERENCES arm.patient (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE arm.rehab_program DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE arm.rehab_program ADD CONSTRAINT doctor_fk FOREIGN KEY (doctor_id)
    REFERENCES arm.doctor (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.program_form | type: TABLE --
-- DROP TABLE IF EXISTS arm.program_form CASCADE;
CREATE TABLE arm.program_form (
                                  id bigserial NOT NULL,
                                  rehab_program_id bigint NOT NULL,
                                  form_id bigint NOT NULL,
                                  type_id bigint NOT NULL,
                                  finished_at timestamp,
                                  score numeric(100,2),
                                  CONSTRAINT program_form_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.program_form OWNER TO postgres;
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE arm.program_form DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE arm.program_form ADD CONSTRAINT form_fk FOREIGN KEY (form_id)
    REFERENCES anketi.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.type | type: TABLE --
-- DROP TABLE IF EXISTS arm.type CASCADE;
CREATE TABLE arm.type (
                          id bigserial NOT NULL,
                          name varchar(255) NOT NULL,
                          CONSTRAINT type_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.type OWNER TO postgres;
-- ddl-end --

-- object: type_fk | type: CONSTRAINT --
-- ALTER TABLE arm.program_form DROP CONSTRAINT IF EXISTS type_fk CASCADE;
ALTER TABLE arm.program_form ADD CONSTRAINT type_fk FOREIGN KEY (type_id)
    REFERENCES arm.type (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE arm.program_form DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE arm.program_form ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES arm.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.module_form | type: TABLE --
-- DROP TABLE IF EXISTS arm.module_form CASCADE;
CREATE TABLE arm.module_form (
                                 id bigserial NOT NULL,
                                 module_id bigint NOT NULL,
                                 form_id bigint NOT NULL,
                                 finished_at timestamp,
                                 score numeric(100,2),
                                 CONSTRAINT module_form_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.module_form OWNER TO postgres;
-- ddl-end --

-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_form DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE arm.module_form ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES arm.module (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_form DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE arm.module_form ADD CONSTRAINT form_fk FOREIGN KEY (form_id)
    REFERENCES anketi.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: program_form_fk | type: CONSTRAINT --
-- ALTER TABLE arm.program_form_answer DROP CONSTRAINT IF EXISTS program_form_fk CASCADE;
ALTER TABLE arm.program_form_answer ADD CONSTRAINT program_form_fk FOREIGN KEY (program_form_id)
    REFERENCES arm.program_form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: arm.module_form_answer | type: TABLE --
-- DROP TABLE IF EXISTS arm.module_form_answer CASCADE;
CREATE TABLE arm.module_form_answer (
                                        id bigserial NOT NULL,
                                        module_form_id bigint NOT NULL,
                                        variant_id bigint NOT NULL,
                                        CONSTRAINT module_form_answer_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE arm.module_form_answer OWNER TO postgres;
-- ddl-end --

-- object: module_form_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_form_answer DROP CONSTRAINT IF EXISTS module_form_fk CASCADE;
ALTER TABLE arm.module_form_answer ADD CONSTRAINT module_form_fk FOREIGN KEY (module_form_id)
    REFERENCES arm.module_form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: variant_fk | type: CONSTRAINT --
-- ALTER TABLE arm.module_form_answer DROP CONSTRAINT IF EXISTS variant_fk CASCADE;
ALTER TABLE arm.module_form_answer ADD CONSTRAINT variant_fk FOREIGN KEY (variant_id)
    REFERENCES anketi.variant (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


