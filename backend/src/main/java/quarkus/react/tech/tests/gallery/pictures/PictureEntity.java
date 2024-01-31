package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import quarkus.react.tech.tests.gallery.PictureInGalleryEntity;
import quarkus.react.tech.tests.gallery.galleries.GalleryEntity;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(
        name = "pictures"
)
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

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getDatatype() {
        return datatype;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public byte[] getData() {
        return data;
    }

    public Timestamp getUploaded() {
        return uploaded;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public PictureEntity setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public PictureEntity setDatatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    public PictureEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public PictureEntity setUploaded(Timestamp uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public PictureEntity setEdited(Timestamp edited) {
        this.edited = edited;
        return this;
    }

    public PictureEntity setData(byte[] data) {
        this.data = data;
        return this;
    }

    public PictureEntity setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
