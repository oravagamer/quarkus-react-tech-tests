CREATE SEQUENCE IF NOT EXISTS galleries_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE galleries
(
    id          BIGINT                      NOT NULL,
    name        VARCHAR(50)                 NOT NULL,
    description VARCHAR(255),
    created     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    edited      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    thumbnail   BIGINT,
    CONSTRAINT pk_galleries PRIMARY KEY (id)
);

ALTER TABLE galleries
    ADD CONSTRAINT uc_galleries_name UNIQUE (name);

ALTER TABLE galleries
    ADD CONSTRAINT FK_GALLERIES_ON_THUMBNAIL FOREIGN KEY (thumbnail) REFERENCES pictures (id) ON DELETE CASCADE;