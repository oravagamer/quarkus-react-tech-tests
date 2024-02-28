CREATE TRIGGER on_gal_update
    BEFORE UPDATE
    ON galleries
    FOR EACH ROW
EXECUTE PROCEDURE on_galleries_update();