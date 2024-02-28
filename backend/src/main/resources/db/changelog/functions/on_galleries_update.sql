CREATE FUNCTION on_galleries_update() RETURNS TRIGGER
    LANGUAGE PLPGSQL AS
'
    BEGIN
        NEW.edited := CURRENT_TIMESTAMP;
        RETURN NEW;
    END;
';