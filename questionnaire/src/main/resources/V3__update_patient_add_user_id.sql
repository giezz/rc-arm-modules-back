-- [ Created objects ] --
-- object: _user_id | type: COLUMN --
-- ALTER TABLE doc.patient DROP COLUMN IF EXISTS _user_id CASCADE;
ALTER TABLE doc.patient ADD COLUMN _user_id bigint NOT NULL DEFAULT 2;
-- ddl-end --




-- [ Created foreign keys ] --
-- object: _user_fk | type: CONSTRAINT --
-- ALTER TABLE doc.patient DROP CONSTRAINT IF EXISTS _user_fk CASCADE;
ALTER TABLE doc.patient ADD CONSTRAINT _user_fk FOREIGN KEY (_user_id)
    REFERENCES doc._user (id) MATCH FULL
    ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --