package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "PicturesOrderDAO.readPictureOrder",
                query = "SELECT pic.id, pic.description, pic.uploaded, pic.edited " +
                        "FROM PictureInGalleryEntity AS pig " +
                        "JOIN PictureEntity as pic " +
                        "ON pic.id = pig.id.pid " +
                        "WHERE pig.id.gid = ?1" +
                        "ORDER BY pig.ord"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.countPicturesInGallery",
                query = "SELECT COUNT(*) " +
                        "FROM PictureInGalleryEntity AS pig " +
                        "WHERE pig.id.gid = ?1"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.getUnOrderedPictures",
                query = "SELECT pig.id.pid " +
                        "FROM PictureInGalleryEntity AS pig " +
                        "WHERE pig.id.gid = ?1 AND pig.id.pid NOT IN ?2  " +
                        "ORDER BY pig.ord"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.changeOrder",
                query = "UPDATE PictureInGalleryEntity AS pig " +
                        "SET pig.ord = ?1 " +
                        "WHERE pig.id.pid = ?2 " +
                        "AND pig.id.gid = ?3"
        )
})
public class PicturesOrderDAO extends PanacheEntityBase {
    @Id
    private Long id;
    private String description;
    private Timestamp uploaded;
    private Timestamp edited;

    public static List<PicturesOrderDAO> findPicturesByGID(Long gid) {
        return find("#PicturesOrderDAO.readPictureOrder", gid).list();
    }

    public static void addPictureToGallery(Long pid, Long gid) {
        new PictureInGalleryEntity()
                .setId(
                        new PictureInGalleryID()
                                .setPid(pid)
                                .setGid(gid)
                )
                .setPicture(PictureEntity.findById(pid))
                .setGallery(GalleryEntity.findById(gid))
                .setOrd(count("#PicturesOrderDAO.countPicturesInGallery", gid) + 1)
                .persist();
    }

    public static List<PicturesOrderDAO> reOrderPicturesInGallery(List<Long> pids, Long gid) {
        List<Long> list = new ArrayList<>();
        list.addAll(pids);
        list.addAll(
                find("#PicturesOrderDAO.getUnOrderedPictures", gid, pids)
                        .stream()
                        .map(Long.class::cast)
                        .toList()
        );

        Log.info(list.toString());

        for (int i = 0; i < list.size(); i++) {
            update("#PicturesOrderDAO.changeOrder", i + 1, list.get(i), gid);
        }
        return findPicturesByGID(gid);
    }
}
