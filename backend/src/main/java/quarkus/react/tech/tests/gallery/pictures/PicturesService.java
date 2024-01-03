package quarkus.react.tech.tests.gallery.pictures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.multipart.FormValue;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;
import quarkus.react.tech.tests.DbDAO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

import org.jboss.logging.Logger;

import java.io.IOException;

@ApplicationScoped
public class PicturesService {
    @Inject
    PicturesDAO picturesDAO;

    @Inject
    DbDAO dbDAO;

    Logger logger = Logger.getLogger(PicturesService.class);

    public Response downloadPicture(Long id) {
        PictureDTO pictureDTO = picturesDAO.findPictureById(id);
        try {
            Response.ResponseBuilder response = Response.ok(pictureDTO.getData());
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

    public void changeDescription(Long id, String description) {
        dbDAO.createUpdateDelete("UPDATE galleries SET description = ? WHERE id = ?", description, id);
    }

    public void deletePicture(Long id) {
        dbDAO.createUpdateDelete("DELETE FROM pictures WHERE id = ?", id);
    }
}
