CREATE SEQUENCE galleries_id_seq AS BIGINT START 1 INCREMENT 1;

CREATE TABLE galleries
(
    id          BIGINT      NOT NULL UNIQUE PRIMARY KEY DEFAULT nextval('galleries_id_seq'::regclass),
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created     TIMESTAMP   NOT NULL,
    edited      TIMESTAMP   NOT NULL
);