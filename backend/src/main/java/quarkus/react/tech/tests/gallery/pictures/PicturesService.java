package quarkus.react.tech.tests.gallery.pictures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.multipart.FormValue;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;
import quarkus.react.tech.tests.gallery.PictureInGalleryRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ApplicationScoped
public class PicturesService {

    Logger logger = Logger.getLogger(PicturesService.class);

    @Inject
    PictureInGalleryRepository pictureInGalleryRepository;

    public Response downloadPicture(Long id) throws SQLException, IOException {

        PictureEntity entity = PictureEntity.findById(id);
        Response.ResponseBuilder response = Response.ok(entity.getData());
        response.header("Content-Disposition", "filename=" + entity.getFilename() + "." + entity.getDatatype());
        response.header("Content-Type", "image/*");
        return response.build();
    }

    public void uploadPicture(MultipartFormDataInput dataInput) throws IOException {
        FormValue file = dataInput.getValues().get("file").iterator().next();
        String desc = dataInput.getValues().get("description").iterator().next().getValue();
        PictureEntity entity = new PictureEntity();
        entity
                .setFilename(file.getFileName().substring(0, file.getFileName().lastIndexOf(".")))
                .setDatatype(file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1))
                .setDescription(desc)
                .setUploaded(Timestamp.valueOf(LocalDateTime.now()))
                .setEdited(Timestamp.valueOf(LocalDateTime.now()))
                .setData(file.getFileItem().getInputStream().readAllBytes())
                .persist();
        pictureInGalleryRepository.addPictureToGallery(entity.getId(), 1L);
    }

    public void changeDescription(Long id, String description) {
        PictureEntity.update("description = ?1 WHERE id = ?2", description, id);
    }

    public void deletePicture(Long id) {
        PictureEntity.deleteById(id);
    }
}
