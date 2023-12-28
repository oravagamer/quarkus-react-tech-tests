package quarkus.react.tech.tests.gallery.galleries;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.MyExceptions;

import java.util.ArrayList;

@Path("galleries")
public class GalleriesResource {

    @Inject
    GalleriesService service;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<Response> getGalleriesInfo() {
        return Uni.createFrom().item(service.getGalleriesInfo());
    }

    @Path("{id}/gal-name")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void changeGalleryName(String name,
                                  @PathParam("id") Long id) throws MyExceptions.AlreadyExistsException {
        if (id != 1) {
            service.changeGalName(id, name);
        }
    }

    @Path("{id}/gal-desc")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void changeGalleryDescription(String description,
                                         @PathParam("id") Long id) {
        service.changeGalDescription(id, description);
    }

    @Path("")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void createGallery(@FormParam("name") String name,
                              @FormParam("description") String description) throws MyExceptions.AlreadyExistsException {
        service.createGallery(name, description);
    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void deleteGallery(@PathParam("id") Long id) {
        if (id != 1) {
            service.deleteGallery(id);
        }
    }

    @Path("{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<Response> getGalleryInfo(@PathParam("id") Long id) {
        return Uni.createFrom().item(service.getGalleryInfo(id));
    }

    @Path("{id}/ord")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<Response> changePictureOrder(ArrayList<Long> ids,
                                            @PathParam("id") Long gid) {
        return Uni.createFrom().item(service.changePictureOrder(ids, gid));
    }

    @Path("{gid}/{pid}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void addPictureToGallery(@PathParam("gid") Long gid,
                                  @PathParam("pid") Long pid) {
        service.addPictureToGallery(pid, gid);
    }

    @Path("{gid}/{pid}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    public void removePictureFromGallery(@PathParam("gid") Long gid,
                                  @PathParam("pid") Long pid) {
        service.removePictureFromGallery(pid, gid);
    }


}
