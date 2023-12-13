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


-- object: doc | type: SCHEMA --
-- DROP SCHEMA IF EXISTS doc CASCADE;
CREATE SCHEMA doc;
-- ddl-end --
ALTER SCHEMA doc OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,doc;
-- ddl-end --

-- object: doc.doctor | type: TABLE --
-- DROP TABLE IF EXISTS doc.doctor CASCADE;
CREATE TABLE doc.doctor (
                            id bigserial NOT NULL,
                            doctor_code bigint NOT NULL,
                            first_name varchar(255) NOT NULL,
                            middle_name varchar(255),
                            last_name varchar(255) NOT NULL,
                            email varchar(255) NOT NULL,
                            phone_number varchar(18) NOT NULL,
                            _user_id bigint NOT NULL,
                            CONSTRAINT doctor_pk PRIMARY KEY (id),
                            CONSTRAINT doctor_code_uq UNIQUE (doctor_code)
);
-- ddl-end --
ALTER TABLE doc.doctor OWNER TO postgres;
-- ddl-end --

-- object: doc.patient | type: TABLE --
-- DROP TABLE IF EXISTS doc.patient CASCADE;
CREATE TABLE doc.patient (
                             id bigserial NOT NULL,
                             patient_code bigint NOT NULL,
                             first_name varchar(255) NOT NULL,
                             middle_name varchar(255),
                             last_name varchar(255) NOT NULL,
                             birth_date date NOT NULL,
                             death_date date,
                             address text NOT NULL,
                             phone_number varchar(18) NOT NULL,
                             work_place_data text NOT NULL,
                             bookmark text,
                             snils char(11) NOT NULL,
                             polis char(16) NOT NULL,
                             patient_status_id bigint NOT NULL,
                             passport_id bigint NOT NULL,
                             doctor_id bigint,
                             CONSTRAINT patient_pk PRIMARY KEY (id),
                             CONSTRAINT patient_code_uq UNIQUE (patient_code)
);
-- ddl-end --
ALTER TABLE doc.patient OWNER TO postgres;
-- ddl-end --

-- object: doc.form | type: TABLE --
-- DROP TABLE IF EXISTS doc.form CASCADE;
CREATE TABLE doc.form (
                          id bigserial NOT NULL,
                          name text NOT NULL,
                          description text,
                          scale_id bigint,
                          CONSTRAINT form_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.form OWNER TO postgres;
-- ddl-end --

-- object: doc.exercise | type: TABLE --
-- DROP TABLE IF EXISTS doc.exercise CASCADE;
CREATE TABLE doc.exercise (
                              id bigserial NOT NULL,
                              name text NOT NULL,
                              video_url varchar(2083) NOT NULL,
                              description text,
                              exercise_type_id bigint,
                              CONSTRAINT exercise_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.exercise OWNER TO postgres;
-- ddl-end --

-- object: doc.patient_status | type: TABLE --
-- DROP TABLE IF EXISTS doc.patient_status CASCADE;
CREATE TABLE doc.patient_status (
                                    id bigserial NOT NULL,
                                    name text NOT NULL,
                                    CONSTRAINT patient_status_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.patient_status OWNER TO postgres;
-- ddl-end --

-- object: patient_status_fk | type: CONSTRAINT --
-- ALTER TABLE doc.patient DROP CONSTRAINT IF EXISTS patient_status_fk CASCADE;
ALTER TABLE doc.patient ADD CONSTRAINT patient_status_fk FOREIGN KEY (patient_status_id)
    REFERENCES doc.patient_status (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.rehab_program | type: TABLE --
-- DROP TABLE IF EXISTS doc.rehab_program CASCADE;
CREATE TABLE doc.rehab_program (
                                   id bigserial NOT NULL,
                                   patient_id bigint NOT NULL,
                                   doctor_id bigint,
                                   is_current boolean NOT NULL,
                                   start_date date NOT NULL,
                                   end_date date,
                                   start_form_id bigint NOT NULL,
                                   end_form_id bigint NOT NULL,
                                   CONSTRAINT rehabilitation_program_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.rehab_program OWNER TO postgres;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE doc.rehab_program ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
    REFERENCES doc.patient (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.module | type: TABLE --
-- DROP TABLE IF EXISTS doc.module CASCADE;
CREATE TABLE doc.module (
                            id bigserial NOT NULL,
                            name text NOT NULL,
                            CONSTRAINT module_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.module OWNER TO postgres;
-- ddl-end --

-- object: doc.block | type: TABLE --
-- DROP TABLE IF EXISTS doc.block CASCADE;
CREATE TABLE doc.block (
                           id bigserial NOT NULL,
                           name text NOT NULL,
                           CONSTRAINT block_type_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.block OWNER TO postgres;
-- ddl-end --

-- object: start_form_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program DROP CONSTRAINT IF EXISTS start_form_fk CASCADE;
ALTER TABLE doc.rehab_program ADD CONSTRAINT start_form_fk FOREIGN KEY (start_form_id)
    REFERENCES doc.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: end_form_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program DROP CONSTRAINT IF EXISTS end_form_fk CASCADE;
ALTER TABLE doc.rehab_program ADD CONSTRAINT end_form_fk FOREIGN KEY (end_form_id)
    REFERENCES doc.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.protocol | type: TABLE --
-- DROP TABLE IF EXISTS doc.protocol CASCADE;
CREATE TABLE doc.protocol (
                              id bigserial NOT NULL,
                              is_final boolean NOT NULL,
                              rehab_result text NOT NULL,
                              recommendations text NOT NULL,
                              rehab_diagnosis text NOT NULL,
                              creation_date date NOT NULL,
                              rehab_program_id bigint NOT NULL,
                              patient_id bigint NOT NULL,
                              CONSTRAINT protocol_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.protocol OWNER TO postgres;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE doc.protocol DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE doc.protocol ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES doc.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.passport | type: TABLE --
-- DROP TABLE IF EXISTS doc.passport CASCADE;
CREATE TABLE doc.passport (
                              id bigserial NOT NULL,
                              series char(4) NOT NULL,
                              number char(6) NOT NULL,
                              issued text NOT NULL,
                              issued_date date NOT NULL,
                              CONSTRAINT passport_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.passport OWNER TO postgres;
-- ddl-end --

-- object: passport_fk | type: CONSTRAINT --
-- ALTER TABLE doc.patient DROP CONSTRAINT IF EXISTS passport_fk CASCADE;
ALTER TABLE doc.patient ADD CONSTRAINT passport_fk FOREIGN KEY (passport_id)
    REFERENCES doc.passport (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: passport_uq | type: CONSTRAINT --
-- ALTER TABLE doc.patient DROP CONSTRAINT IF EXISTS passport_uq CASCADE;
ALTER TABLE doc.patient ADD CONSTRAINT passport_uq UNIQUE (passport_id);
-- ddl-end --

-- object: doc.question | type: TABLE --
-- DROP TABLE IF EXISTS doc.question CASCADE;
CREATE TABLE doc.question (
                              id bigserial NOT NULL,
                              content text NOT NULL,
                              CONSTRAINT question_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.question OWNER TO postgres;
-- ddl-end --

-- object: doc.answer | type: TABLE --
-- DROP TABLE IF EXISTS doc.answer CASCADE;
CREATE TABLE doc.answer (
                            id bigserial NOT NULL,
                            patient_id bigint NOT NULL,
                            variant_id bigint,
                            answered_at date NOT NULL,
                            CONSTRAINT answer_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.answer OWNER TO postgres;
-- ddl-end --

-- object: doc.form_question | type: TABLE --
-- DROP TABLE IF EXISTS doc.form_question CASCADE;
CREATE TABLE doc.form_question (
                                   id_form bigint NOT NULL,
                                   id_question bigint NOT NULL,
                                   CONSTRAINT form_question_pk PRIMARY KEY (id_form,id_question)
);
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE doc.form_question DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE doc.form_question ADD CONSTRAINT form_fk FOREIGN KEY (id_form)
    REFERENCES doc.form (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: question_fk | type: CONSTRAINT --
-- ALTER TABLE doc.form_question DROP CONSTRAINT IF EXISTS question_fk CASCADE;
ALTER TABLE doc.form_question ADD CONSTRAINT question_fk FOREIGN KEY (id_question)
    REFERENCES doc.question (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE doc.answer DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE doc.answer ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
    REFERENCES doc.patient (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.scale | type: TABLE --
-- DROP TABLE IF EXISTS doc.scale CASCADE;
CREATE TABLE doc.scale (
                           id bigserial NOT NULL,
                           name text NOT NULL,
                           description text,
                           CONSTRAINT scale_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.scale OWNER TO postgres;
-- ddl-end --

-- object: scale_fk | type: CONSTRAINT --
-- ALTER TABLE doc.form DROP CONSTRAINT IF EXISTS scale_fk CASCADE;
ALTER TABLE doc.form ADD CONSTRAINT scale_fk FOREIGN KEY (scale_id)
    REFERENCES doc.scale (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.exercise_type | type: TABLE --
-- DROP TABLE IF EXISTS doc.exercise_type CASCADE;
CREATE TABLE doc.exercise_type (
                                   id bigserial NOT NULL,
                                   name text NOT NULL,
                                   CONSTRAINT exercise_type_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.exercise_type OWNER TO postgres;
-- ddl-end --

-- object: exercise_type_fk | type: CONSTRAINT --
-- ALTER TABLE doc.exercise DROP CONSTRAINT IF EXISTS exercise_type_fk CASCADE;
ALTER TABLE doc.exercise ADD CONSTRAINT exercise_type_fk FOREIGN KEY (exercise_type_id)
    REFERENCES doc.exercise_type (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE doc.rehab_program ADD CONSTRAINT doctor_fk FOREIGN KEY (doctor_id)
    REFERENCES doc.doctor (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.job_position | type: TABLE --
-- DROP TABLE IF EXISTS doc.job_position CASCADE;
CREATE TABLE doc.job_position (
                                  id bigserial NOT NULL,
                                  title text NOT NULL,
                                  CONSTRAINT position_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.job_position OWNER TO postgres;
-- ddl-end --

-- object: doc.job_specialization | type: TABLE --
-- DROP TABLE IF EXISTS doc.job_specialization CASCADE;
CREATE TABLE doc.job_specialization (
                                        id bigserial NOT NULL,
                                        title text NOT NULL,
                                        CONSTRAINT job_specialization_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.job_specialization OWNER TO postgres;
-- ddl-end --

-- object: doc.doctor_specialization | type: TABLE --
-- DROP TABLE IF EXISTS doc.doctor_specialization CASCADE;
CREATE TABLE doc.doctor_specialization (
                                           job_specialization_id bigint NOT NULL,
                                           doctor_id bigint NOT NULL,
                                           CONSTRAINT doctor_specialization_pk PRIMARY KEY (job_specialization_id,doctor_id) DEFERRABLE INITIALLY IMMEDIATE
);
-- ddl-end --

-- object: job_specialization_fk | type: CONSTRAINT --
-- ALTER TABLE doc.doctor_specialization DROP CONSTRAINT IF EXISTS job_specialization_fk CASCADE;
ALTER TABLE doc.doctor_specialization ADD CONSTRAINT job_specialization_fk FOREIGN KEY (job_specialization_id)
    REFERENCES doc.job_specialization (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY IMMEDIATE;
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE doc.doctor_specialization DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE doc.doctor_specialization ADD CONSTRAINT doctor_fk FOREIGN KEY (doctor_id)
    REFERENCES doc.doctor (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.doctor_position | type: TABLE --
-- DROP TABLE IF EXISTS doc.doctor_position CASCADE;
CREATE TABLE doc.doctor_position (
                                     doctor_id bigint NOT NULL,
                                     job_position_id bigint NOT NULL,
                                     CONSTRAINT doctor_position_pk PRIMARY KEY (doctor_id,job_position_id)
);
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE doc.doctor_position DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE doc.doctor_position ADD CONSTRAINT doctor_fk FOREIGN KEY (doctor_id)
    REFERENCES doc.doctor (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: job_position_fk | type: CONSTRAINT --
-- ALTER TABLE doc.doctor_position DROP CONSTRAINT IF EXISTS job_position_fk CASCADE;
ALTER TABLE doc.doctor_position ADD CONSTRAINT job_position_fk FOREIGN KEY (job_position_id)
    REFERENCES doc.job_position (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.variant | type: TABLE --
-- DROP TABLE IF EXISTS doc.variant CASCADE;
CREATE TABLE doc.variant (
                             id bigserial NOT NULL,
                             content text NOT NULL,
                             score numeric(100,2) NOT NULL,
                             question_id bigint NOT NULL,
                             CONSTRAINT answer_variant_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.variant OWNER TO postgres;
-- ddl-end --

-- object: question_fk | type: CONSTRAINT --
-- ALTER TABLE doc.variant DROP CONSTRAINT IF EXISTS question_fk CASCADE;
ALTER TABLE doc.variant ADD CONSTRAINT question_fk FOREIGN KEY (question_id)
    REFERENCES doc.question (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: variant_fk | type: CONSTRAINT --
-- ALTER TABLE doc.answer DROP CONSTRAINT IF EXISTS variant_fk CASCADE;
ALTER TABLE doc.answer ADD CONSTRAINT variant_fk FOREIGN KEY (variant_id)
    REFERENCES doc.variant (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.module_exercise | type: TABLE --
-- DROP TABLE IF EXISTS doc.module_exercise CASCADE;
CREATE TABLE doc.module_exercise (
                                     module_id bigint NOT NULL,
                                     id_exercise bigint NOT NULL,
                                     block_id bigint NOT NULL,
                                     CONSTRAINT module_exercise_pk PRIMARY KEY (id_exercise,module_id)
);
-- ddl-end --

-- object: exercise_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS exercise_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT exercise_fk FOREIGN KEY (id_exercise)
    REFERENCES doc.exercise (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.module_form | type: TABLE --
-- DROP TABLE IF EXISTS doc.module_form CASCADE;
CREATE TABLE doc.module_form (
                                 module_id bigint NOT NULL,
                                 form_id bigint NOT NULL,
                                 block_id bigint NOT NULL,
                                 CONSTRAINT module_form_pk PRIMARY KEY (module_id,form_id)
);
-- ddl-end --

-- object: doc.program_module | type: TABLE --
-- DROP TABLE IF EXISTS doc.program_module CASCADE;
CREATE TABLE doc.program_module (
                                    id serial NOT NULL,
                                    finished_at date,
                                    module_id bigint NOT NULL,
                                    rehab_program_id bigint NOT NULL,
                                    CONSTRAINT program_module_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE doc.program_module DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE doc.program_module ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES doc.module (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE doc.program_module DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE doc.program_module ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES doc.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES doc.module (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: block_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS block_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT block_fk FOREIGN KEY (block_id)
    REFERENCES doc.block (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: block_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_form DROP CONSTRAINT IF EXISTS block_fk CASCADE;
ALTER TABLE doc.module_form ADD CONSTRAINT block_fk FOREIGN KEY (block_id)
    REFERENCES doc.block (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_form DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE doc.module_form ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES doc.module (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_form DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE doc.module_form ADD CONSTRAINT form_fk FOREIGN KEY (form_id)
    REFERENCES doc.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.rehab_program_log | type: TABLE --
-- DROP TABLE IF EXISTS doc.rehab_program_log CASCADE;
CREATE TABLE doc.rehab_program_log (
                                       id bigserial NOT NULL,
                                       rehab_program_id bigint NOT NULL,
                                       who_changed bigint NOT NULL,
                                       change_date date NOT NULL,
                                       change text NOT NULL,
                                       operation text NOT NULL,
                                       CONSTRAINT rehab_program_log_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.rehab_program_log OWNER TO postgres;
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program_log DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE doc.rehab_program_log ADD CONSTRAINT doctor_fk FOREIGN KEY (who_changed)
    REFERENCES doc.doctor (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rehab_program_fk | type: CONSTRAINT --
-- ALTER TABLE doc.rehab_program_log DROP CONSTRAINT IF EXISTS rehab_program_fk CASCADE;
ALTER TABLE doc.rehab_program_log ADD CONSTRAINT rehab_program_fk FOREIGN KEY (rehab_program_id)
    REFERENCES doc.rehab_program (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.interpretation | type: TABLE --
-- DROP TABLE IF EXISTS doc.interpretation CASCADE;
CREATE TABLE doc.interpretation (
                                    id bigserial NOT NULL,
                                    min_value numeric(100,2) NOT NULL,
                                    max_value numeric(100,2) NOT NULL,
                                    description text NOT NULL,
                                    scale_id bigint NOT NULL,
                                    CONSTRAINT interpretation_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.interpretation OWNER TO postgres;
-- ddl-end --

-- object: scale_fk | type: CONSTRAINT --
-- ALTER TABLE doc.interpretation DROP CONSTRAINT IF EXISTS scale_fk CASCADE;
ALTER TABLE doc.interpretation ADD CONSTRAINT scale_fk FOREIGN KEY (scale_id)
    REFERENCES doc.scale (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE doc.protocol DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE doc.protocol ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
    REFERENCES doc.patient (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.form_result | type: TABLE --
-- DROP TABLE IF EXISTS doc.form_result CASCADE;
CREATE TABLE doc.form_result (
                                 id bigserial NOT NULL,
                                 form_id bigint NOT NULL,
                                 score numeric(100,2) NOT NULL,
                                 creation_date date NOT NULL,
                                 patient_id bigint NOT NULL,
                                 CONSTRAINT form_result_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.form_result OWNER TO postgres;
-- ddl-end --

-- object: form_fk | type: CONSTRAINT --
-- ALTER TABLE doc.form_result DROP CONSTRAINT IF EXISTS form_fk CASCADE;
ALTER TABLE doc.form_result ADD CONSTRAINT form_fk FOREIGN KEY (form_id)
    REFERENCES doc.form (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.protocol_form_result | type: TABLE --
-- DROP TABLE IF EXISTS doc.protocol_form_result CASCADE;
CREATE TABLE doc.protocol_form_result (
                                          protocol_id bigint NOT NULL,
                                          form_result_id bigint NOT NULL,
                                          CONSTRAINT protocol_form_result_pk PRIMARY KEY (protocol_id,form_result_id)
);
-- ddl-end --

-- object: protocol_fk | type: CONSTRAINT --
-- ALTER TABLE doc.protocol_form_result DROP CONSTRAINT IF EXISTS protocol_fk CASCADE;
ALTER TABLE doc.protocol_form_result ADD CONSTRAINT protocol_fk FOREIGN KEY (protocol_id)
    REFERENCES doc.protocol (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: form_result_fk | type: CONSTRAINT --
-- ALTER TABLE doc.protocol_form_result DROP CONSTRAINT IF EXISTS form_result_fk CASCADE;
ALTER TABLE doc.protocol_form_result ADD CONSTRAINT form_result_fk FOREIGN KEY (form_result_id)
    REFERENCES doc.form_result (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: patient_fk | type: CONSTRAINT --
-- ALTER TABLE doc.form_result DROP CONSTRAINT IF EXISTS patient_fk CASCADE;
ALTER TABLE doc.form_result ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id)
    REFERENCES doc.patient (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc._user | type: TABLE --
-- DROP TABLE IF EXISTS doc._user CASCADE;
CREATE TABLE doc._user (
                           id bigserial NOT NULL,
                           login varchar(255) NOT NULL,
                           password varchar(255) NOT NULL,
                           CONSTRAINT _user_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc._user OWNER TO postgres;
-- ddl-end --

-- object: doc._role | type: TABLE --
-- DROP TABLE IF EXISTS doc._role CASCADE;
CREATE TABLE doc._role (
                           id bigserial NOT NULL,
                           name varchar(255) NOT NULL,
                           CONSTRAINT _role_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc._role OWNER TO postgres;
-- ddl-end --

-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE doc.doctor DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE doc.doctor ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES doc._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doc.user_role | type: TABLE --
-- DROP TABLE IF EXISTS doc.user_role CASCADE;
CREATE TABLE doc.user_role (
                               _user_id bigint NOT NULL,
                               _role_id bigint NOT NULL,
                               CONSTRAINT user_role_pk PRIMARY KEY (_user_id,_role_id)
);
-- ddl-end --

-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE doc.user_role DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE doc.user_role ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES doc._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: _role_fk | type: CONSTRAINT --
-- ALTER TABLE doc.user_role DROP CONSTRAINT IF EXISTS _role_fk CASCADE;
ALTER TABLE doc.user_role ADD CONSTRAINT _role_fk FOREIGN KEY (_role_id)
    REFERENCES doc._role (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: doctor_fk | type: CONSTRAINT --
-- ALTER TABLE doc.patient DROP CONSTRAINT IF EXISTS doctor_fk CASCADE;
ALTER TABLE doc.patient ADD CONSTRAINT doctor_fk FOREIGN KEY (doctor_id)
    REFERENCES doc.doctor (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


