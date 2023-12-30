-- [ Created objects ] --
-- object: id | type: COLUMN --
-- ALTER TABLE doc.module_exercise DROP COLUMN IF EXISTS id CASCADE;
ALTER TABLE doc.module_exercise ADD COLUMN id bigserial NOT NULL;
-- ddl-end --


-- object: finished_at | type: COLUMN --
-- ALTER TABLE doc.module_exercise DROP COLUMN IF EXISTS finished_at CASCADE;
ALTER TABLE doc.module_exercise ADD COLUMN finished_at timestamp;
-- ddl-end --


-- object: id | type: COLUMN --
-- ALTER TABLE doc.module_form DROP COLUMN IF EXISTS id CASCADE;
ALTER TABLE doc.module_form ADD COLUMN id bigserial NOT NULL;
-- ddl-end --


-- object: exercise_id | type: COLUMN --
-- ALTER TABLE doc.module_exercise DROP COLUMN IF EXISTS exercise_id CASCADE;
ALTER TABLE doc.module_exercise ADD COLUMN exercise_id bigint NOT NULL;
-- ddl-end --


-- object: finished_at | type: COLUMN --
-- ALTER TABLE doc.module_form DROP COLUMN IF EXISTS finished_at CASCADE;
ALTER TABLE doc.module_form ADD COLUMN finished_at timestamp;
-- ddl-end --




-- [ Changed objects ] --
ALTER TABLE doc.module ALTER COLUMN finished_at TYPE timestamp;