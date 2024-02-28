CREATE TRIGGER on_pic_update
    BEFORE UPDATE
    ON pictures
    FOR EACH ROW
EXECUTE PROCEDURE on_pictures_update();

CREATE TRIGGER on_pic_insert
    AFTER INSERT
    ON pictures
    FOR EACH ROW
    EXECUTE PROCEDURE on_pictures_insert();
