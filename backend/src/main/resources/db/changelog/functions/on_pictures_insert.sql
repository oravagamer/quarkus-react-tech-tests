CREATE FUNCTION on_pictures_insert() RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
'
    DECLARE
        default_gallery BIGINT;
        pic_count       BIGINT;
    BEGIN
        SELECT id
        INTO default_gallery
        FROM quarkus_tests.galleries
        ORDER BY created DESC
        LIMIT 1;

        SELECT COUNT(*)
        INTO pic_count
        FROM quarkus_tests.pic_in_gallery AS pig
        WHERE pig.gallery_id = default_gallery;

        INSERT INTO quarkus_tests.pic_in_gallery(pic_order, picture_id, gallery_id)
        VALUES (pic_count + 1, NEW.id,
                default_gallery);
        return NEW;
    END;
'
