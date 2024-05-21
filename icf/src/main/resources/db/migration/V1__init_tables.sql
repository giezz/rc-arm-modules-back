-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.4
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file.
-- These commands were put in this file only as a convenience.
--
-- object: rc_icf | type: DATABASE --
-- DROP DATABASE IF EXISTS rc_icf;
-- CREATE DATABASE rc_icf
--     TEMPLATE = template0
--     LC_COLLATE = 'ru_RU'
--     LC_CTYPE = 'ru_RU';
-- ddl-end --


-- object: spravochnik | type: SCHEMA --
-- DROP SCHEMA IF EXISTS spravochnik CASCADE;
CREATE SCHEMA spravochnik;
-- ddl-end --
ALTER SCHEMA spravochnik OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,spravochnik;
-- ddl-end --

-- object: spravochnik.category | type: TABLE --
-- DROP TABLE IF EXISTS spravochnik.category CASCADE;
CREATE TABLE spravochnik.category (
                                      code varchar(20) NOT NULL,
                                      name TEXT NOT NULL,
                                      description TEXT,
                                      parent_id varchar(20),
                                      has_children BOOLEAN DEFAULT TRUE,
                                      CONSTRAINT classification_pk PRIMARY KEY (code)
);
-- ddl-end --
ALTER TABLE spravochnik.category OWNER TO postgres;
-- ddl-end --

-- object: category_parent_fk | type: CONSTRAINT --
-- ALTER TABLE spravochnik.category DROP CONSTRAINT IF EXISTS category_parent_fk CASCADE;
ALTER TABLE spravochnik.category ADD CONSTRAINT category_parent_fk FOREIGN KEY (parent_id)
    REFERENCES spravochnik.category (code) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


