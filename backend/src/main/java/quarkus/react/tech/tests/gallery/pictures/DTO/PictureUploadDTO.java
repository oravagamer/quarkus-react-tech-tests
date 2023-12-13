package quarkus.react.tech.tests.gallery.pictures.DTO;

import io.vertx.mutiny.ext.web.FileUpload;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.FilePart;
import org.jboss.resteasy.reactive.PartFilename;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.server.multipart.FormValue;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.InputStream;

public class PictureUploadDTO {
    private FormValue file;
    private String description;

    public PictureUploadDTO(FormValue file, String description) {
        this.file = file;
        this.description = description;
    }

    public FormValue getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }
}
