-- [ Created objects ] --
-- object: creation_date | type: COLUMN --
-- ALTER TABLE doc.rehab_program DROP COLUMN IF EXISTS creation_date CASCADE;
ALTER TABLE doc.rehab_program ADD COLUMN creation_date timestamp;
-- ddl-end --


-- [ Changed objects ] --
ALTER TABLE doc.rehab_program ALTER COLUMN start_date DROP NOT NULL;