package quarkus.react.tech.tests.gallery;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntity;
import quarkus.react.tech.tests.gallery.pictures.PictureEntityDTO;

import java.sql.Timestamp;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "PicturesOrderDAO.readPictureOrder",
                query = "SELECT pic.id, description, uploaded, edited " +
                        "FROM pic_in_gallery AS pig " +
                        "JOIN pictures AS pic " +
                        "ON pig.picture_id = pic.id " +
                        "WHERE pig.gallery_id = ? " +
                        "ORDER BY pig.pic_order",
                resultSetMapping = "PictureEntityDTO"
        )
})
@NamedQueries({
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
@SqlResultSetMapping(
        name = "PictureEntityDTO",
        classes = {
                @ConstructorResult(
                        targetClass = PictureEntityDTO.class,
                        columns = {
                                @ColumnResult(
                                        name = "id",
                                        type = Long.class
                                ),
                                @ColumnResult(
                                        name = "description",
                                        type = String.class
                                ),
                                @ColumnResult(
                                        name = "uploaded",
                                        type = Timestamp.class
                                ),
                                @ColumnResult(
                                        name = "edited",
                                        type = Timestamp.class
                                )
                        }
                )
        }
)
@Entity
@Table(
        name = "pic_in_gallery"
)
@Getter
@Builder(
        setterPrefix = "set"
)
@AllArgsConstructor
@NoArgsConstructor
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

}
