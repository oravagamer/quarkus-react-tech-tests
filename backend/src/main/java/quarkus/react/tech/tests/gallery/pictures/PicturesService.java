package quarkus.react.tech.tests.gallery.pictures;

import io.smallrye.common.annotation.NonBlocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.multipart.FormValue;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class PicturesService {
    @Inject
    PicturesDAO picturesDAO;

    Logger logger = Logger.getLogger(PicturesService.class);

    public Response downloadPicture(Long id) {
        PictureDTO pictureDTO = picturesDAO.findPictureById(id);
        try {
            logger.info("Transaction started");
            Response.ResponseBuilder response = Response.ok(pictureDTO.getData());
            logger.info("Transaction ended.");
            response.header("Content-Disposition", "filename=" + pictureDTO.getFilename() + "." + pictureDTO.getDatatype());
            response.header("Content-Type", "image/*");
            return response.build();
        } catch (NullPointerException ex) {
            logger.error(ex.getMessage());
        }
        return Response.serverError().build();
    }

    public void uploadPicture(MultipartFormDataInput dataInput) throws IOException {
        FormValue file = dataInput.getValues().get("file").iterator().next();
        String desc = dataInput.getValues().get("description").iterator().next().getValue();
        picturesDAO.insert(new PictureUploadDTO(file.getFileItem().getInputStream(), file.getFileName(), desc));
    }
}
