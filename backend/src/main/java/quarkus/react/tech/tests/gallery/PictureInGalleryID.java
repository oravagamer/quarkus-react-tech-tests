package quarkus.react.tech.tests.gallery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
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

    public Long getPid() {
        return pid;
    }

    public PictureInGalleryID setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public Long getGid() {
        return gid;
    }

    public PictureInGalleryID setGid(Long gid) {
        this.gid = gid;
        return this;
    }

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
