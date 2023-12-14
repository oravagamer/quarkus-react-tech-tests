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
    private final InputStream file;
    private final String description;

    private final String fileName;

    public PictureUploadDTO(InputStream file, String fileName, String description) {
        this.file = file;
        this.fileName = fileName;
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public InputStream getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }
}
