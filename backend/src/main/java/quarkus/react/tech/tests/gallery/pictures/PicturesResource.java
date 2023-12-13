package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.runtime.BlockingOperationControl;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

import java.io.InputStream;

@Path("/picture")
public class PicturesResource {
    @Inject
    PicturesService service;

    @Path("/{id}")
    @GET
    @Produces("image/*")
    @Blocking
    public Uni<Response> downloadPicture(@PathParam("id") Long id) {
        return Uni.createFrom().item(service.downloadPicture(id));
    }

    @Path("/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public Uni<Response> uploadPicture(MultipartFormDataInput multipartFormDataInput) {
        service.uploadPicture(multipartFormDataInput);
        return Uni.createFrom().item(Response.ok().build());
    }
}
