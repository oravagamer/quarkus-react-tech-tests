package quarkus.react.tech.tests.gallery.galleries;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.ConfigDataSource;
import quarkus.react.tech.tests.DbDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@ApplicationScoped
public class GalleriesService {
    @Inject
    ConfigDataSource dataSource;

    Logger logger = Logger.getLogger(GalleriesService.class);

    @Inject
    DbDAO dbDAO;

    public Response getGalleriesInfo() {
        return Response.ok(dbDAO.read("SELECT id, name, description, created, edited, pid FROM galleries JOIN pic_in_gal pig on galleries.id = pig.gid WHERE thumbnail = TRUE ORDER BY created")).build();
    }

    public void createGallery(String name, String description) {
        dbDAO.createUpdateDelete("INSERT INTO galleries(name, description, created, edited) VALUES (?, ?, current_timestamp, current_timestamp)", name, description);
    }

    public void changeGalName(Long id, String name) {
        dbDAO.createUpdateDelete("UPDATE galleries SET name = ?, edited = current_timestamp WHERE id = ?", name, id);
    }

    public void changeGalDescription(Long id, String description) {
        dbDAO.createUpdateDelete("UPDATE galleries SET description = ?, edited = current_timestamp WHERE id = ?", description, id);
    }

    public void deleteGallery(Long id) {
        dbDAO.createUpdateDelete("DELETE FROM galleries WHERE id = ?", id);
    }

    public Response getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(dbDAO.read("SELECT * FROM galleries WHERE id = ?", id).get(0));
        list.add(dbDAO.read("SELECT id, description, uploaded, edited FROM picture_info WHERE gid = ?", id));
        return Response.ok().entity(list).build();
    }

    public Response changePictureOrder(ArrayList<Long> ids, Long gid) {
        Connection c = dataSource.getConnection();
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT pid FROM pic_in_gal WHERE gid = ? AND NOT ? @> ARRAY[pid] ORDER BY pic_order");
            ps.setLong(1, gid);
            ps.setArray(2, c.createArrayOf("BIGINT", ids.toArray()));
            ResultSet rs = ps.executeQuery();
            ArrayList<Long> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getLong("pid"));
            }
            rs.close();
            ps.close();
            ids.addAll(list);
            ps = c.prepareStatement("UPDATE pic_in_gal SET pic_order = cast(array_position(?, pid) AS BIGINT) WHERE gid = ?");
            ps.setArray(1, c.createArrayOf("BIGINT", ids.toArray()));
            ps.setLong(2, gid);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("SELECT id, description, uploaded, edited FROM picture_info WHERE gid = ?");
            ps.setLong(1, gid);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, Object> val = new HashMap<>();
                val.put("id", rs.getLong("id"));
                val.put("description", rs.getString("description"));
                val.put("uploaded", rs.getTimestamp("uploaded"));
                val.put("edited", rs.getTimestamp("edited"));
                result.add(val);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return Response.ok().entity(result).build();
    }

    public void removePictureFromGallery(Long pid, Long gid) {
        dbDAO.createUpdateDelete("DELETE FROM pic_in_gal WHERE pid = ? AND gid = ?", pid, gid);
    }

    public void addPictureToGallery(Long pid, Long gid) {
        dbDAO.createUpdateDelete("INSERT INTO pic_in_gal(pid, gid, pic_order, thumbnail) VALUES(?, ?, (SELECT CASE WHEN max(pic_order) IS NULL THEN 1 ELSE max(pic_order) END FROM pic_in_gal WHERE gid = ?), (SELECT CASE WHEN count(thumbnail) = 1 THEN FALSE ELSE TRUE END FROM pic_in_gal WHERE gid = ? AND thumbnail = TRUE))", pid, gid, gid, gid);

    }

    public void setPictureAsThumbnail(Long pid, Long gid) {
        dbDAO.createUpdateDelete("UPDATE pic_in_gal SET thumbnail = FALSE WHEN thumbnail = TRUE AND gid = ?", gid);
        dbDAO.createUpdateDelete("UPDATE pic_in_gal SET thumbnail = TRUE WHERE pid = ? AND gid = ?", pid, gid);
    }
}
