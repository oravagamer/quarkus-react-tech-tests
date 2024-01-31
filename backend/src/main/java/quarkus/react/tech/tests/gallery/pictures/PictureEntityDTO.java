package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;
import java.sql.Timestamp;

@RegisterForReflection
public record PictureEntityDTO(Long id,
                               String description,
                               Timestamp uploaded,
                               Timestamp edited) implements Serializable {

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "description = " + description + ", " +
                "uploaded = " + uploaded + ", " +
                "edited = " + edited + ")";
    }
}