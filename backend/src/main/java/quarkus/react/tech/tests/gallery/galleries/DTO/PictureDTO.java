package quarkus.react.tech.tests.gallery.galleries.DTO;

import java.sql.Date;

public class PictureDTO {
    private Long id;
    private String description;
    private Date uploaded;
    private Date edited;

    public PictureDTO(Long id, String description, Date uploaded, Date edited) {
        this.id = id;
        this.description = description;
        this.uploaded = uploaded;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public Date getEdited() {
        return edited;
    }
}
