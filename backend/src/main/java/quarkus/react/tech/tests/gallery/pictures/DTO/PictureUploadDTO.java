package quarkus.react.tech.tests.gallery.pictures.DTO;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import quarkus.react.tech.tests.UploadItemSchema;

@Schema(implementation = UploadItemSchema.class)
public class PictureUploadDTO {

    @FormParam("file")
    @PartType("image/*")
    private FileUpload file;

    @FormParam("description")
    @PartType(MediaType.TEXT_PLAIN)
    private String description;

    public FileUpload getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }

    private static class UploadFormSchema {
        public UploadItemSchema file;
    }
}
