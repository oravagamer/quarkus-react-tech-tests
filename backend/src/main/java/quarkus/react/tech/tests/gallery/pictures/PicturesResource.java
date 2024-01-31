package quarkus.react.tech.tests.gallery.pictures;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
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
    @Operation(
            summary = "Upload picture",
            description = "Upload of picture file"
    )
    public Uni<Response> uploadPicture(
            @RequestBody(
                    description = "test",
                    name = "file",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM
                            )
                    }
            )
            MultipartFormDataInput multipartFormDataInput
    ) throws IOException {
        service.uploadPicture(multipartFormDataInput);
        return Uni.createFrom().item(Response.ok().build());
    }

//    @Path("jhugtfdvfcrdfyt")
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.TEXT_PLAIN)
//    public void Test(
//            PicturesUploadForm form
//    ) {
//        Log.info(form.fileUpload.length);
//    }

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
