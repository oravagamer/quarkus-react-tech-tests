package quarkus.react.tech.tests.gallery.galleries;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.gallery.galleries.DTO.GalleryDataDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@ApplicationScoped
public class GalleriesService {

    Logger logger = Logger.getLogger(GalleriesService.class);

    public Response getGalleriesInfo() {
        return Response.ok(GalleryEntity.findAll(Sort.by("created")).project(GalleryDataDTO.class).list()).build();
    }

    public void createGallery(String name, String description) {
        GalleryEntity entity = new GalleryEntity();
        try {
            entity
                    .setCreated(Timestamp.valueOf(LocalDateTime.now()))
                    .setEdited(Timestamp.valueOf(LocalDateTime.now()))
                    .setName(name)
                    .setDescription(description)
                    .persist();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void changeGalName(Long id, String name) {
        GalleryEntity.update("name = ?1, edited = current_timestamp WHERE id = ?2", name, id);
    }

    public void changeGalDescription(Long id, String description) {
        GalleryEntity.update("description = ?1, edited = current_timestamp WHERE id = ?2", description, id);
    }

    public void deleteGallery(Long id) {
        GalleryEntity.deleteById(id);
    }

    public Response getGalleryInfo(Long id) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(GalleryEntity.find("id", id).project(GalleryDataDTO.class).firstResult());
//        list.add(dbDAO.read("SELECT id, description, uploaded, edited FROM picture_info WHERE gid = ?", id));
        return Response.ok().entity(list).build();
    }

    public Response changePictureOrder(ArrayList<Long> ids, Long gid) {
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
//        try {
//            PreparedStatement ps = c.prepareStatement("SELECT pid FROM pic_in_gal WHERE gid = ? AND NOT ? @> ARRAY[pid] ORDER BY pic_order");
//            ps.setLong(1, gid);
//            ps.setArray(2, c.createArrayOf("BIGINT", ids.toArray()));
//            ResultSet rs = ps.executeQuery();
//            ArrayList<Long> list = new ArrayList<>();
//            while (rs.next()) {
//                list.add(rs.getLong("pid"));
//            }
//            rs.close();
//            ps.close();
//            ids.addAll(list);
//            ps = c.prepareStatement("UPDATE pic_in_gal SET pic_order = cast(array_position(?, pid) AS BIGINT) WHERE gid = ?");
//            ps.setArray(1, c.createArrayOf("BIGINT", ids.toArray()));
//            ps.setLong(2, gid);
//            ps.executeUpdate();
//            ps.close();
//            ps = c.prepareStatement("SELECT id, description, uploaded, edited FROM picture_info WHERE gid = ?");
//            ps.setLong(1, gid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HashMap<String, Object> val = new HashMap<>();
//                val.put("id", rs.getLong("id"));
//                val.put("description", rs.getString("description"));
//                val.put("uploaded", rs.getTimestamp("uploaded"));
//                val.put("edited", rs.getTimestamp("edited"));
//                result.add(val);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage());
//        } finally {
//            try {
//                c.close();
//            } catch (SQLException ex) {
//                logger.error(ex.getMessage());
//            }
//        }
        return Response.ok().entity(result).build();
    }

    public void removePictureFromGallery(Long pid, Long gid) {
//        dbDAO.createUpdateDelete("DELETE FROM pic_in_gal WHERE pid = ? AND gid = ?", pid, gid);
    }

    public void addPictureToGallery(Long pid, Long gid) {
//        dbDAO.createUpdateDelete("INSERT INTO pic_in_gal(pid, gid, pic_order, thumbnail) VALUES(?, ?, (SELECT CASE WHEN max(pic_order) IS NULL THEN 1 ELSE max(pic_order) END FROM pic_in_gal WHERE gid = ?), (SELECT CASE WHEN count(thumbnail) = 1 THEN FALSE ELSE TRUE END FROM pic_in_gal WHERE gid = ? AND thumbnail = TRUE))", pid, gid, gid, gid);

    }

    public void setPictureAsThumbnail(Long pid, Long gid) {
//        dbDAO.createUpdateDelete("UPDATE galleries SET thumbnail = ? WHERE gid = ?", pid, gid);
    }
}
