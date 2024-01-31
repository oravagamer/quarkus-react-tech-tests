package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "pic_in_gallery"
)
@NamedQueries({
        @NamedQuery(
                name = "PicturesOrderDAO.readPictureOrder",
                query = "SELECT pic.id, pic.description, pic.uploaded, pic.edited " +
                        "FROM PictureInGalleryEntity pig " +
                        "JOIN PictureEntity pic " +
                        "ON pic.id = pig.id.pid " +
                        "WHERE pig.id.gid = ?1" +
                        "ORDER BY pig.ord"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.countPicturesInGallery",
                query = "SELECT COUNT(*) " +
                        "FROM PictureInGalleryEntity pig " +
                        "WHERE pig.id.gid = ?1"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.getUnOrderedPictures",
                query = "SELECT pig.id.pid " +
                        "FROM PictureInGalleryEntity pig " +
                        "WHERE pig.id.gid = ?1 AND pig.id.pid NOT IN ?2  " +
                        "ORDER BY pig.ord"
        ),
        @NamedQuery(
                name = "PicturesOrderDAO.changeOrder",
                query = "UPDATE PictureInGalleryEntity pig " +
                        "SET pig.ord = ?1 " +
                        "WHERE pig.id.pid = ?2 " +
                        "AND pig.id.gid = ?3"
        )
})
public class PictureInGalleryEntity extends PanacheEntityBase {
    @EmbeddedId
    private PictureInGalleryID id;

    @Column(
            name = "pic_order",
            nullable = false
    )
    Long ord;

    @ManyToOne
    @MapsId("pictureId")
    @JoinColumn(name = "picture_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    PictureEntity picture;

    @ManyToOne
    @MapsId("galleryId")
    @JoinColumn(name = "gallery_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    GalleryEntity gallery;

    public PictureInGalleryEntity setId(PictureInGalleryID id) {
        this.id = id;
        return this;
    }

    public PictureInGalleryEntity setOrd(Long ord) {
        this.ord = ord;
        return this;
    }

    public PictureInGalleryEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public PictureInGalleryEntity setGallery(GalleryEntity gallery) {
        this.gallery = gallery;
        return this;
    }
}
