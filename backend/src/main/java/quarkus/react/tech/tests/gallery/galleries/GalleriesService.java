package quarkus.react.tech.tests.gallery.galleries;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import quarkus.react.tech.tests.DbDAO;
import quarkus.react.tech.tests.MyExceptions;

import java.util.ArrayList;

@ApplicationScoped
public class GalleriesService {

    @Inject
    DbDAO dbDAO;

    public Response getGalleriesInfo() {
        return Response.ok(dbDAO.read("SELECT * FROM galleries ORDER BY created")).build();
    }

    public void createGallery(String name, String description) throws MyExceptions.AlreadyExistsException {
        dbDAO.createUpdateDelete("INSERT INTO galleries(name, description, created, edited) VALUES (?, ?, current_timestamp, current_timestamp)", name, description);
    }

    public void changeGalName(Long id, String name) throws MyExceptions.AlreadyExistsException {
        dbDAO.createUpdateDelete("UPDATE galleries SET name = ?, edited = current_timestamp WHERE id = ?", name, id);
    }

    public void changeGalDescription(Long id, String description) {
        dbDAO.createUpdateDelete("UPDATE galleries SET description = ?, edited = current_timestamp WHERE id = ?", description, id);
    }

    public void deleteGallery(Long id) {
        dbDAO.createUpdateDelete("DELETE FROM galleries WHERE id = ?", id);
    }

    public Response getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(dbDAO.read("SELECT * FROM galleries WHERE id = ?", id));
        list.add(dbDAO.read("SELECT pictures.id, pictures.description, pictures.uploaded, pictures.edited FROM pic_in_gal JOIN pictures ON pic_in_gal.pid = pictures.id WHERE pic_in_gal.gid = ? ORDER BY pic_order", id));
        return Response.ok().entity(list).build();
    }

    public Response changePictureOrder(ArrayList<Long> ids) {
        return Response.ok().entity(ids).build();
    }
}
