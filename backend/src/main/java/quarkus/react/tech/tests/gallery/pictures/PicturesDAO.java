package quarkus.react.tech.tests.gallery.pictures;

import quarkus.react.tech.tests.GenericDAO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;

public interface PicturesDAO extends GenericDAO<Picture, Long> {
    public PictureDTO findPictureById(Long id);
}
