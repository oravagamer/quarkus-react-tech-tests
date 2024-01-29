package quarkus.react.tech.tests.gallery.pictures;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.sql.SQLException;

@Path("picture")
public class PicturesResource {
    @Inject
    PicturesService service;

    @Path("{id}")
    @GET
    @Produces("image/*")
    @Transactional
    public Uni<Response> downloadPicture(@PathParam("id") Long id) throws SQLException, IOException {
        return Uni.createFrom().item(service.downloadPicture(id));
    }

    @Path("")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Uni<Response> uploadPicture(MultipartFormDataInput multipartFormDataInput) throws IOException {
        service.uploadPicture(multipartFormDataInput);
        return Uni.createFrom().item(Response.ok().build());
    }

    @Path("{id}/pic-desc")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public void changeDescription(String description,
                                  @PathParam("id") Long id) {
        service.changeDescription(id, description);
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public void deletePicture(@PathParam("id") Long id) {
        service.deletePicture(id);
    }
}
