package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntity;

@Entity
@Table(
        name = "pic_in_gallery"
)
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
    PictureEntity picture;

    @ManyToOne
    @MapsId("galleryId")
    @JoinColumn(name = "gallery_id")
    GalleryEntity gallery;
}
