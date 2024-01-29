package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import quarkus.react.tech.tests.gallery.PictureInGalleryEntity;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(
        name = "galleries"
)
public class GalleryEntity extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "galleriesSequence",
            sequenceName = "galleries_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "galleriesSequence")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true,
            length = 50
    )
    private String name;

    @Column(
            name = "description",
            nullable = true,
            length = 255
    )
    private String description;

    @Column(
            name = "created",
            nullable = false
    )
    private Timestamp created;

    @Column(
            name = "edited",
            nullable = false
    )
    private Timestamp edited;

    @OneToMany(mappedBy = "gallery")
    Set<PictureInGalleryEntity> pictureInGallerySet;

    public GalleryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public GalleryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public GalleryEntity setCreated(Timestamp created) {
        this.created = created;
        return this;
    }

    public GalleryEntity setEdited(Timestamp edited) {
        this.edited = edited;
        return this;
    }
}
