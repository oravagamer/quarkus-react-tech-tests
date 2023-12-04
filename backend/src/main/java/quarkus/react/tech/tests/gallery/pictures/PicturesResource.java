package quarkus.react.tech.tests.gallery.pictures;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

@Path("/picture")
public class PicturesResource {
    @Inject
    PicturesService service;

    @Path("/{id}")
    @GET
    @Produces("image/*")
    public Uni<Response> downloadPicture(@PathParam("id") Long id) {
        return Uni.createFrom().item(service.downloadPicture(id));
    }

    @Path("/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> uploadPicture(PictureUploadDTO pictureUploadDTO) {
        service.uploadPicture(pictureUploadDTO);
        return Uni.createFrom().item(Response.ok().build());
    }
}
