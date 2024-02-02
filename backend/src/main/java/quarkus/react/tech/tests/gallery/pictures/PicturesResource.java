package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.FileDTO;

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
        FileDTO file = service.downloadPicture(id);
        Response.ResponseBuilder response;
        try {
            response = Response.ok(file.data());
        } catch (NullPointerException ex) {
            return Uni
                    .createFrom()
                    .item(
                            Response
                                    .status(
                                            Response.Status.NOT_FOUND
                                    )
                                    .build()
                    );
        }
        response.header("Content-Disposition", "filename=" + file.filename() + "." + file.datatype());
        response.header("Content-Type", "image/*");
        Log.info(file.toString());
        return Uni
                .createFrom()
                .item(response.build());
    }

    @Path("")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Uni<Response> uploadPicture(
            @BeanParam PicturesUploadMultipartForm multipartForm
    ) throws IOException {
        service.uploadPicture(
                multipartForm.getFiles(),
                multipartForm.getDescriptions()
        );
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
