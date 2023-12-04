package quarkus.react.tech.tests.gallery.pictures;

import java.io.InputStream;

public class Picture {
    private Long id;
    private String filename;
    private String datatype;
    private String description;
    private InputStream thumbnail;
    private InputStream data;

    public Picture(Long id, String filename, String datatype, String description, InputStream thumbnail, InputStream data) {
        this.id = id;
        this.filename = filename;
        this.datatype = datatype;
        this.description = description;
        this.thumbnail = thumbnail;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(InputStream thumbnail) {
        this.thumbnail = thumbnail;
    }


    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }
}
