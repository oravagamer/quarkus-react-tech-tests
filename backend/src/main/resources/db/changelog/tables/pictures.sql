CREATE SEQUENCE IF NOT EXISTS pictures_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE pictures
(
    id          BIGINT                      NOT NULL DEFAULT nextval('pictures_id_seq'),
    filename    VARCHAR(50)                 NOT NULL,
    datatype    VARCHAR(5)                  NOT NULL,
    description VARCHAR(255),
    thumbnail   BYTEA,
    data        BYTEA                       NOT NULL,
    uploaded    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    edited      TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_pictures PRIMARY KEY (id)
);