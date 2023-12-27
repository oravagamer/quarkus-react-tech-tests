package quarkus.react.tech.tests.gallery.galleries.DTO;

import java.sql.Date;

public class GalleryDTO {
    Long id;

    String name;
    String description;
    Date created;
    Date edited;

    public GalleryDTO(Long id, String name, String description, Date created, Date edited) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }
}
