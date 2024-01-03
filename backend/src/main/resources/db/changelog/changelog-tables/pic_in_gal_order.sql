CREATE TABLE pic_in_gal
(
    pid BIGINT NOT NULL,
    gid BIGINT NOT NULL,
    pic_order BIGINT NOT NULL,
    thumbnail BOOLEAN NOT NULL,
    PRIMARY KEY (pid, gid),
    CONSTRAINT fk_pid FOREIGN KEY (pid) REFERENCES  pictures(id) ON DELETE CASCADE,
    CONSTRAINT fk_gid FOREIGN KEY (gid) REFERENCES galleries(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX unique_thumbnail ON pic_in_gal(gid, thumbnail) WHERE thumbnail = TRUE;
