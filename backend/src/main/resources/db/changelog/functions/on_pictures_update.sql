CREATE FUNCTION on_pictures_update() RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
'
    BEGIN
        NEW.edited := CURRENT_TIMESTAMP;
        return NEW;
    END;
';