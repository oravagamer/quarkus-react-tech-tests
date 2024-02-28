CREATE FUNCTION on_pictures_insert() RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
'
    DECLARE
        default_gallery BIGINT;
        pic_count       BIGINT;
    BEGIN
        SELECT id
        FROM galleries
        ORDER BY created DESC
        LIMIT 1
        INTO default_gallery;

        SELECT COUNT(*)
        FROM pic_in_gallery AS pig
        WHERE pig.gallery_id = default_gallery
        INTO pic_count;

        INSERT INTO pic_in_gallery(pic_order, picture_id, gallery_id)
        VALUES (pic_count + 1, NEW.id,
                default_gallery);
        return NEW;
    END;
'
