CREATE TABLE pic_in_gallery
(
    pic_order  BIGINT NOT NULL,
    picture_id BIGINT NOT NULL,
    gallery_id BIGINT NOT NULL,
    CONSTRAINT pk_pic_in_gallery PRIMARY KEY (picture_id, gallery_id)
);

ALTER TABLE pic_in_gallery
    ADD CONSTRAINT FK_PIC_IN_GALLERY_ON_GALLERY FOREIGN KEY (gallery_id) REFERENCES galleries (id) ON DELETE CASCADE;

ALTER TABLE pic_in_gallery
    ADD CONSTRAINT FK_PIC_IN_GALLERY_ON_PICTURE FOREIGN KEY (picture_id) REFERENCES pictures (id) ON DELETE CASCADE;