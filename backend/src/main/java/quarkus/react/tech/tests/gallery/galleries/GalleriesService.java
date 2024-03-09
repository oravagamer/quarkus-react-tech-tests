package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.gallery.PictureInGalleryDAO;
import quarkus.react.tech.tests.gallery.PictureInGalleryEntity;
import quarkus.react.tech.tests.gallery.PictureInGalleryID;
import quarkus.react.tech.tests.gallery.pictures.PictureEntityDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GalleriesService {

    Logger logger = Logger.getLogger(GalleriesService.class);

    @Inject
    PictureInGalleryDAO pictureInGalleryDAO;

    public List<GalleryEntityDTO> getGalleriesInfo() {
        return GalleryEntity.findAll(Sort.by("created")).project(GalleryEntityDTO.class).list();
    }

    public void createGallery(String name, String description) {
        try {
            GalleryEntity.builder().setName(name).setDescription(description).build().persist();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void changeGalName(Long id, String name) {
        GalleryEntity.update("name = ?1 WHERE id = ?2", name, id);
    }

    public void changeGalDescription(Long id, String description) {
        GalleryEntity.update("description = ?1 WHERE id = ?2", description, id);
    }

    public void deleteGallery(Long id) {
        GalleryEntity.deleteById(id);
    }

    public List<Object> getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(GalleryEntity.find("id", id).project(GalleryEntityDTO.class).stream().map(value -> new GalleryEntityDTO(value.id(), value.name(), value.description(), value.created(), value.edited(), value.thumbnail() == null ? GalleryEntity.find("limit 1", Sort.by("created", Sort.Direction.Ascending)).project(GalleryEntityDTO.class).singleResult().id() : value.thumbnail())).findFirst());
        list.add(pictureInGalleryDAO.findPicturesByGID(id));
        return list;
    }

    public List<PictureEntityDTO> changePictureOrder(ArrayList<Long> ids, Long gid) {
        return pictureInGalleryDAO.reOrderPicturesInGallery(ids, gid);
    }

    public void removePictureFromGallery(Long pid, Long gid) {
        PictureInGalleryEntity.deleteById(PictureInGalleryID.builder().setPid(pid).setGid(gid).build());
    }

    public void addPictureToGallery(Long pid, Long gid) {
        pictureInGalleryDAO.addPictureToGallery(pid, gid);

    }

    public void setPictureAsThumbnail(Long pid, Long gid) {
        GalleryEntity.update("thumbnail = ?1 WHERE id = ?2", pid, gid);
    }
}
