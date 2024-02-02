package quarkus.react.tech.tests.gallery.pictures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import quarkus.react.tech.tests.FileDTO;
import quarkus.react.tech.tests.gallery.PictureInGalleryDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PicturesService {

    Logger logger = Logger.getLogger(PicturesService.class);

    @Inject
    PictureInGalleryDAO pictureInGalleryDAO;

    public FileDTO downloadPicture(Long id) {

        return PictureEntity
                .find("id", id)
                .project(FileDTO.class)
                .firstResult();
    }

    public void uploadPicture(List<FileUpload> files, List<String> descriptions) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            String filename = files.get(i).fileName();
            PictureEntity entity = PictureEntity
                    .builder()
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
                    .build();
            entity.persist();
            pictureInGalleryDAO.addPictureToGallery(entity.getId(), 1L);
        }
    }

    public void changeDescription(Long id, String description) {
        PictureEntity.update(
                "description = ?1, edited = ?2 WHERE id = ?3",
                description,
                Timestamp.valueOf(LocalDateTime.now()),
                id
        );
    }

    public void deletePicture(Long id) {
        PictureEntity.deleteById(id);
    }
}
