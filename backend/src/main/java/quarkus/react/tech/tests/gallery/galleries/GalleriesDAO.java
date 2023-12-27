package quarkus.react.tech.tests.gallery.galleries;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.ConfigDataSource;
import quarkus.react.tech.tests.JDBCRollback;
import quarkus.react.tech.tests.MyExceptions;
import quarkus.react.tech.tests.gallery.galleries.DTO.GalleryDTO;
import quarkus.react.tech.tests.gallery.galleries.DTO.PictureDTO;

import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
public class GalleriesDAO {
    @Inject
    ConfigDataSource dataSource;

    Logger logger = Logger.getLogger(GalleriesDAO.class);

    public ArrayList<GalleryDTO> readGalleriesInfo() {
        Connection c = dataSource.getConnection();
        ArrayList<GalleryDTO> result = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM galleries ORDER BY created;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new GalleryDTO(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("created"),
                        rs.getDate("edited")));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;

    }

    public void createGallery(String name, String descpription) throws MyExceptions.AlreadyExistsException {
        Connection c = dataSource.getConnection();
        Savepoint sp = null;
        try {
            c.setAutoCommit(false);
            sp = c.setSavepoint();
            PreparedStatement ps = c.prepareStatement("INSERT INTO galleries(name, description, created, edited) VALUES (?, ?, current_timestamp, current_timestamp)");
            ps.setString(1, name);
            ps.setString(2, descpription);
            ps.executeUpdate();
            c.commit();
            c.setAutoCommit(true);
        } catch (SQLException ex) {
            JDBCRollback.rollback(c, sp);
            logger.error(ex.getMessage());
            throw new MyExceptions.AlreadyExistsException(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void updateGalleryName(Long id, String name) throws MyExceptions.AlreadyExistsException {
        Connection c = dataSource.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE galleries SET name = ?, edited = current_timestamp WHERE id = ?");
            ps.setString(1, name);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new MyExceptions.AlreadyExistsException(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void updateGalleryDescription(Long id, String description) {
        Connection c = dataSource.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE galleries SET description = ?, edited = current_timestamp WHERE id = ?");
            ps.setString(1, description);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void deleteGallery(Long id) {
        Connection c = dataSource.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM galleries WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public ArrayList<PictureDTO> readPicturesInfo(Long id) {
        Connection c = dataSource.getConnection();
        ArrayList<PictureDTO> result = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT pictures.id, pictures.description, pictures.uploaded, pictures.edited FROM pic_in_gal JOIN pictures ON pic_in_gal.pid = pictures.id WHERE pic_in_gal.gid = ? ORDER BY pic_order");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new PictureDTO(
                        rs.getLong("id"),
                        rs.getString("description"),
                        rs.getDate("uploaded"),
                        rs.getDate("edited")));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    public GalleryDTO readGalleryInfo(Long id) {
        Connection c = dataSource.getConnection();
        GalleryDTO galleryDTO = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM galleries WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            galleryDTO = new GalleryDTO(rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("created"),
                    rs.getDate("edited"));
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return galleryDTO;
    }

    public ArrayList<PictureDTO> findPicturesExclude(ArrayList<Long> ids) {

    }

}
