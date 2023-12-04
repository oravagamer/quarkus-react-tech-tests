package quarkus.react.tech.tests.gallery.pictures.DTO;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.reactive.server.multipart.FormValue;

public class PictureUploadDTO {

    @FormParam("file")
    private FormValue file;

    public FormValue getFile() {
        return file;
    }
}
