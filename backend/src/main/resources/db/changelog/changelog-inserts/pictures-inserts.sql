INSERT INTO pictures(filename, datatype, description, data, uploaded, edited)
VALUES ('Default thumbnail',
        'jpg',
        'Default thumbnail',
        get_file_contents('./pictures/default-gallery-settings.jpg'),
        current_timestamp,
        current_timestamp
       );