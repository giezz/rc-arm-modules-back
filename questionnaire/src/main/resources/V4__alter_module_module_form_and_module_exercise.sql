SET search_path=public,pg_catalog,doc;

DROP TABLE IF EXISTS doc.module_form CASCADE;

DROP TABLE IF EXISTS doc.module_exercise CASCADE;

-- object: doc.module_exercise | type: TABLE --
-- DROP TABLE IF EXISTS doc.module_exercise CASCADE;
CREATE TABLE doc.module_exercise (
                                     id bigserial NOT NULL,
                                     module_id bigint NOT NULL,
                                     exercise_id bigint NOT NULL,
                                     block_id bigint NOT NULL,
                                     finished_at timestamp,
                                     CONSTRAINT module_exercise_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.module_exercise OWNER TO postgres;
-- ddl-end --

-- object: doc.module_form | type: TABLE --
-- DROP TABLE IF EXISTS doc.module_form CASCADE;
CREATE TABLE doc.module_form (
                                 id bigserial NOT NULL,
                                 module_id bigint NOT NULL,
                                 form_id bigint NOT NULL,
                                 block_id bigint NOT NULL,
                                 finished_at timestamp,
                                 CONSTRAINT module_form_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE doc.module_form OWNER TO postgres;
-- ddl-end --

-- [ Created foreign keys ] --
-- object: module_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS module_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT module_fk FOREIGN KEY (module_id)
    REFERENCES doc.module (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: exercise_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS exercise_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT exercise_fk FOREIGN KEY (exercise_id)
    REFERENCES doc.exercise (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: block_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_exercise DROP CONSTRAINT IF EXISTS block_fk CASCADE;
ALTER TABLE doc.module_exercise ADD CONSTRAINT block_fk FOREIGN KEY (block_id)
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

-- object: block_fk | type: CONSTRAINT --
-- ALTER TABLE doc.module_form DROP CONSTRAINT IF EXISTS block_fk CASCADE;
ALTER TABLE doc.module_form ADD CONSTRAINT block_fk FOREIGN KEY (block_id)
    REFERENCES doc.block (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --
