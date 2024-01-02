package quarkus.react.tech.tests.gallery.pictures;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.ConfigDataSource;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

import java.sql.*;

@RequestScoped
public class PicturesDAO {
    @Inject
    ConfigDataSource dataSource;
    Logger logger = Logger.getLogger(PicturesDAO.class);

    Connection c = null;

    PictureDTO findPictureById(Long id) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT filename, datatype, data FROM pictures WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            PictureDTO pictureDTO = new PictureDTO(rs.getString("filename"),
                    rs.getString("datatype"),
                    rs.getBinaryStream("data"));
            rs.close();
            ps.close();
            return pictureDTO;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                c.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    void insert(PictureUploadDTO pictureUploadDTO) {
        Savepoint sp = null;
        try {
            sp = c.setSavepoint();
            PreparedStatement ps = c.prepareStatement("INSERT INTO pictures(filename, datatype, description, data, uploaded, edited) VALUES (?, ?, ?, ?, current_timestamp, current_timestamp) RETURNING id");
            String filename = pictureUploadDTO.getFileName();
            ps.setString(1, filename.substring(0, filename.lastIndexOf(".")));
            ps.setString(2, filename.substring(filename.lastIndexOf(".") + 1));
            ps.setString(3, pictureUploadDTO.getDescription());
            ps.setBinaryStream(4, pictureUploadDTO.getFile());
            ResultSet rs = ps.executeQuery();
            rs.next();
            long id = rs.getLong("id");
            rs.close();
            ps.close();
            ps = c.prepareStatement("INSERT INTO pic_in_gal(pid, gid, pic_order) VALUES (?, 1, coalesce((SELECT max(pic_order) + 1 FROM pic_in_gal), 1))");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                c.rollback(sp);
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    @PostConstruct
    void init() {
        c = dataSource.getConnection();
    }

    @PreDestroy
    void destroy() {
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
