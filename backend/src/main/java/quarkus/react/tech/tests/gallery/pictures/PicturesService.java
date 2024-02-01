package quarkus.react.tech.tests.gallery.pictures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import quarkus.react.tech.tests.gallery.PictureInGalleryDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PicturesService {

    Logger logger = Logger.getLogger(PicturesService.class);

    @Inject
    PictureInGalleryDAO pictureInGalleryDAO;

    public Response downloadPicture(Long id) throws SQLException, IOException {

        PictureEntity entity = PictureEntity.findById(id);
        Response.ResponseBuilder response = Response.ok(entity.getData());
        response.header("Content-Disposition", "filename=" + entity.getFilename() + "." + entity.getDatatype());
        response.header("Content-Type", "image/*");
        return response.build();
    }

    public void uploadPicture(List<FileUpload> files, List<String> descriptions) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            PictureEntity entity = new PictureEntity();
            String filename = files.get(i).fileName();
            entity
                    .setFilename(filename.substring(0, filename.lastIndexOf(".")))
                    .setDatatype(filename.substring(filename.lastIndexOf(".") + 1))
                    .setDescription(descriptions.size() == 1 ? descriptions.get(0) : descriptions.get(i))
                    .setUploaded(Timestamp.valueOf(LocalDateTime.now()))
                    .setEdited(Timestamp.valueOf(LocalDateTime.now()))
                    .setData(
                            Files.newInputStream(
                                    files
                                            .get(i)
                                            .uploadedFile()
                            ).readAllBytes()
                    )
                    .persist();
            pictureInGalleryDAO.addPictureToGallery(entity.getId(), 1L);
        }
    }

    public void changeDescription(Long id, String description) {
        PictureEntity.update("description = ?1 WHERE id = ?2", description, id);
    }

    public void deletePicture(Long id) {
        PictureEntity.deleteById(id);
    }
}
