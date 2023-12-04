package quarkus.react.tech.tests.gallery.pictures.DTO;

import java.io.InputStream;

public class PictureDTO {
    private String filename;
    private String datatype;
    private InputStream data;

    public PictureDTO(String filename, String datatype, InputStream data) {
        this.filename = filename;
        this.datatype = datatype;
        this.data = data;
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

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }
}
