package quarkus.react.tech.tests.gallery.pictures;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import quarkus.react.tech.tests.FileUploadSchema;

import java.util.List;


public class PicturesUploadMultipartForm {
    @FormParam("file")
    @Schema(implementation = FileUploadSchema[].class)
    List<FileUpload> files;

    @FormParam("description")
    @PartType(MediaType.APPLICATION_JSON)
    List<String> descriptions;

}
