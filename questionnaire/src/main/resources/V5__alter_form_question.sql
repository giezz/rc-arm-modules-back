SET search_path=public,pg_catalog,doc;

DROP TABLE doc.form_question;

CREATE TABLE doc.form_question (
    id bigserial PRIMARY KEY NOT NULL,
    id_question bigserial NOT NULL,
    id_form bigserial NOT NULL,
    created_at timestamp NULL,
    CONSTRAINT form_fk FOREIGN KEY (id_form)
        REFERENCES doc.form(id) MATCH FULL
        ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT question_fk FOREIGN KEY (id_question)
        REFERENCES doc.question(id) MATCH FULL
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ALTER TABLE doc.form_question ADD CONSTRAINT form_fk FOREIGN KEY (id_form)
--     REFERENCES doc.form (id) MATCH FULL
--     ON DELETE CASCADE ON UPDATE CASCADE;


