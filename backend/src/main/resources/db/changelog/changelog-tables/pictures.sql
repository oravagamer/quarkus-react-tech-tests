CREATE SEQUENCE pictures_id_seq AS BIGINT START 1 INCREMENT 1;

CREATE TABLE pictures
(
    id          BIGINT      NOT NULL UNIQUE PRIMARY KEY DEFAULT nextval('pictures_id_seq'::regclass),
    filename    VARCHAR(50) NOT NULL,
    datatype    VARCHAR(5)  NOT NULL,
    description VARCHAR(255),
    thumbnail   bytea16mb,
    data        bytea16mb       NOT NULL
);