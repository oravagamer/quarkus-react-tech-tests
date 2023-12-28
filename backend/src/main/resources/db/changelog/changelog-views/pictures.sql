CREATE VIEW picture_info AS
SELECT pictures.id, pictures.description, pictures.uploaded, pictures.edited, pic_in_gal.gid
FROM pic_in_gal
JOIN pictures
ON pic_in_gal.pid = pictures.id
ORDER BY pic_in_gal.pic_order;