package quarkus.react.tech.tests.gallery.pictures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import quarkus.react.tech.tests.FileDTO;
import quarkus.react.tech.tests.gallery.PictureInGalleryDAO;

import java.io.IOException;
import java.nio.file.Files;
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
                .singleResult();
    }

    public void uploadPicture(List<FileUpload> files, List<String> descriptions) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            String filename = files.get(i).fileName();
            PictureEntity entity = PictureEntity
                    .builder()
                    .setFilename(filename.substring(0, filename.lastIndexOf(".")))
                    .setDatatype(filename.substring(filename.lastIndexOf(".") + 1))
                    .setDescription(descriptions.size() == 1 ? descriptions.get(0) : descriptions.get(i))
                    .setData(
                            Files.newInputStream(
                                    files
                                            .get(i)
                                            .uploadedFile()
                            ).readAllBytes()
                    )
                    .build();
            entity.persist();
        }
    }

    public void changeDescription(Long id, String description) {
        PictureEntity.update(
                "description = ?1 WHERE id = ?2",
                description,
                id
        );
    }

    public void deletePicture(Long id) {
        PictureEntity.deleteById(id);
    }
}
