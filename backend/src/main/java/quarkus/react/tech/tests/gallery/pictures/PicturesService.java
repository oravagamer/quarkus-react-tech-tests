package quarkus.react.tech.tests.gallery.pictures;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

@Singleton
public class PicturesService {
    @Inject
    PicturesRepository repository;
    public Response downloadPicture(Long id) {
        PictureDTO pictureDTO = repository.findPictureById(id);
        Response.ResponseBuilder response = Response.ok(pictureDTO.getData());
        response.header("Content-Disposition", "filename=" + pictureDTO.getFilename() + "." + pictureDTO.getDatatype());
        return response.build();
    }

    public void uploadPicture(PictureUploadDTO pictureUploadDTO) {

    }
}
