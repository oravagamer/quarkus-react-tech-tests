package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
