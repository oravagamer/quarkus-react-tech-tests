package quarkus.react.tech.tests.gallery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Builder(
        setterPrefix = "set"
)
@AllArgsConstructor
@NoArgsConstructor
public class PictureInGalleryID implements Serializable {
    @Column(
            name = "picture_id",
            nullable = false
    )
    private Long pid;

    @Column(
            name = "gallery_id",
            nullable = false
    )
    private Long gid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureInGalleryID that = (PictureInGalleryID) o;

        if (!Objects.equals(pid, that.pid)) return false;
        return Objects.equals(gid, that.gid);
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        return result;
    }


}
