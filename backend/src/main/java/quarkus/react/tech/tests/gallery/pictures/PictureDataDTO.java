package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

@RegisterForReflection
public class PictureDataDTO implements Serializable {
    private Long id;

    private String filename;

    private String datatype;

    private String description;

    private Blob thumbnail;

    private Blob data;

    private Timestamp uploaded;

    private Timestamp edited;

    public PictureDataDTO(Long id, String filename, String datatype, String description, Blob thumbnail, Blob data, Timestamp uploaded, Timestamp edited) {
        this.id = id;
        this.filename = filename;
        this.datatype = datatype;
        this.description = description;
        this.thumbnail = thumbnail;
        this.data = data;
        this.uploaded = uploaded;
        this.edited = edited;
    }

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

    public Blob getThumbnail() {
        return thumbnail;
    }

    public Blob getData() {
        return data;
    }

    public Timestamp getUploaded() {
        return uploaded;
    }

    public Timestamp getEdited() {
        return edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureDataDTO that = (PictureDataDTO) o;

        if (!id.equals(that.id)) return false;
        if (!filename.equals(that.filename)) return false;
        if (!datatype.equals(that.datatype)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!uploaded.equals(that.uploaded)) return false;
        return edited.equals(that.edited);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + filename.hashCode();
        result = 31 * result + datatype.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + uploaded.hashCode();
        result = 31 * result + edited.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PictureDataDTO{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", datatype='" + datatype + '\'' +
                ", description='" + description + '\'' +
                ", uploaded=" + uploaded +
                ", edited=" + edited +
                '}';
    }
}
