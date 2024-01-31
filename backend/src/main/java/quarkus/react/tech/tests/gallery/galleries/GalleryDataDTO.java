package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@RegisterForReflection
public class GalleryDataDTO implements Serializable {
    private final Long id;
    private final String name;
    private final String description;

    private final Timestamp created;

    private final Timestamp edited;

    private final Long thumbnail;

    public GalleryDataDTO(Long id, String name, String description, Timestamp created, Timestamp edited, Long thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.edited = edited;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public Long getThumbnail() {
        return thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalleryDataDTO entity = (GalleryDataDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.created, entity.created) &&
                Objects.equals(this.edited, entity.edited) &&
                Objects.equals(this.thumbnail, entity.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, edited, thumbnail);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "created = " + created + ", " +
                "edited = " + edited + ", " +
                "thumbnail = " + thumbnail + ")";
    }
}
