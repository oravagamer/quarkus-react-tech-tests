package quarkus.react.tech.tests.gallery.galleries;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.MyExceptions;

import java.util.ArrayList;

@ApplicationScoped
public class GalleriesService {
    @Inject
    GalleriesDAO galleriesDAO;

    public Response getGalleriesInfo() {
        return Response.ok(galleriesDAO.readGalleriesInfo()).build();
    }

    public void createGallery(String name, String descpription) throws MyExceptions.AlreadyExistsException {
        galleriesDAO.createGallery(name, descpription);
    }

    public void changeGalName(Long id, String name) throws MyExceptions.AlreadyExistsException {
        galleriesDAO.updateGalleryName(id, name);
    }

    public void changeGalDescription(Long id, String description) {
        galleriesDAO.updateGalleryDescription(id, description);
    }

    public void deleteGallery(Long id) {
        galleriesDAO.deleteGallery(id);
    }

    public Response getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(galleriesDAO.readGalleryInfo(id));
        list.add(galleriesDAO.readPicturesInfo(id));
        return Response.ok().entity(list).build();
    }

    public Response changePictureOrder(ArrayList<Long> id) {
        return Response.ok().entity().build();
    }
}
