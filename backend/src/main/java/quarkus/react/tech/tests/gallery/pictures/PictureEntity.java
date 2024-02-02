package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quarkus.react.tech.tests.gallery.PictureInGalleryEntity;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(
        name = "pictures"
)
@Getter
@Builder(
        setterPrefix = "set"
)
@AllArgsConstructor
@NoArgsConstructor
public class PictureEntity extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "picturesSequence",
            sequenceName = "pictures_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picturesSequence")
    private Long id;

    @Column(
            name = "filename",
            nullable = false,
            length = 50
    )
    private String filename;

    @Column(
            name = "datatype",
            nullable = false,
            length = 5
    )
    private String datatype;

    @Column(
            name = "description",
            nullable = true,
            length = 255
    )
    private String description;


    @Column(
            name = "thumbnail",
            nullable = true
    )
    private byte[] thumbnail;

    @Column(
            name = "data",
            nullable = false
    )
    private byte[] data;

    @Column(
            name = "uploaded",
            nullable = false
    )
    private Timestamp uploaded;

    @Column(
            name = "edited",
            nullable = false
    )
    private Timestamp edited;

    @OneToMany(mappedBy = "picture")
    Set<PictureInGalleryEntity> pictureInGallerySet;

    @OneToMany(mappedBy = "picture")
    Set<GalleryEntity> gallerySet;
}
