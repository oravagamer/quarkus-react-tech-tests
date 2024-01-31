package quarkus.react.tech.tests.gallery.pictures;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.server.multipart.FileItem;

public class PicturesUploadForm {

    @Schema(
            name = "file",
            implementation = UploadItemSchema[].class
    )
    FileItem[] fileUpload;
}

