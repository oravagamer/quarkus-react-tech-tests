package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;
import java.sql.Timestamp;

@RegisterForReflection
public record GalleryEntityDTO(Long id,
                               String name,
                               String description,
                               Timestamp created,
                               Timestamp edited,
                               Long thumbnail) implements Serializable {

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
