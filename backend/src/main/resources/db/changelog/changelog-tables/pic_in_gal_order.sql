CREATE TABLE pic_in_gal
(
    pid BIGINT NOT NULL,
    gid BIGINT NOT NULL,
    pic_order BIGINT NOT NULL,
    PRIMARY KEY (pid, gid),
    UNIQUE (gid, pic_order),
    CONSTRAINT fk_pid FOREIGN KEY (pid) REFERENCES  pictures(id) ON DELETE CASCADE,
    CONSTRAINT fk_gid FOREIGN KEY (gid) REFERENCES galleries(id) ON DELETE CASCADE
);


