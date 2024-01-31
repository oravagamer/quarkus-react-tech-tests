package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.gallery.PictureInGalleryDAO;
import quarkus.react.tech.tests.gallery.PictureInGalleryEntity;
import quarkus.react.tech.tests.gallery.PictureInGalleryID;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ApplicationScoped
public class GalleriesService {

    Logger logger = Logger.getLogger(GalleriesService.class);

    @Inject
    PictureInGalleryDAO pictureInGalleryDAO;

    public Response getGalleriesInfo() {
        return Response.ok(GalleryEntity.findAll(Sort.by("created")).project(GalleryEntityDTO.class).list()).build();
    }

    public void createGallery(String name, String description) {
        GalleryEntity entity = new GalleryEntity();
        try {
            entity
                    .setCreated(Timestamp.valueOf(LocalDateTime.now()))
                    .setEdited(Timestamp.valueOf(LocalDateTime.now()))
                    .setName(name)
                    .setDescription(description)
                    .persist();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void changeGalName(Long id, String name) {
        GalleryEntity.update("name = ?1, edited = current_timestamp WHERE id = ?2", name, id);
    }

    public void changeGalDescription(Long id, String description) {
        GalleryEntity.update("description = ?1, edited = current_timestamp WHERE id = ?2", description, id);
    }

    public void deleteGallery(Long id) {
        GalleryEntity.deleteById(id);
    }

    public Response getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(
                GalleryEntity
                        .find("id", id)
                        .project(GalleryEntityDTO.class)
                        .firstResult()
        );
        list.add(pictureInGalleryDAO.findPicturesByGID(id));
        return Response.ok().entity(list).build();
    }

    public Response changePictureOrder(ArrayList<Long> ids, Long gid) {
        return Response.ok().entity(pictureInGalleryDAO.reOrderPicturesInGallery(ids, gid)).build();
    }

    public void removePictureFromGallery(Long pid, Long gid) {
        PictureInGalleryEntity.deleteById(
                new PictureInGalleryID()
                        .setPid(pid)
                        .setGid(gid)
        );
    }

    public void addPictureToGallery(Long pid, Long gid) {
        pictureInGalleryDAO.addPictureToGallery(pid, gid);

    }

    public void setPictureAsThumbnail(Long pid, Long gid) {
        GalleryEntity
                .update("thumbnail = ?1 WHERE id = ?2", pid, gid);
    }
}
