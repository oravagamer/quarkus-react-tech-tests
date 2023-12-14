package quarkus.react.tech.tests.gallery.pictures;

import io.quarkus.runtime.BlockingOperationControl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.ConfigDataSource;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureUploadDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class PicturesDAO {
    @Inject
    ConfigDataSource dataSource;
    Logger logger = Logger.getLogger(PicturesDAO.class);


    PictureDTO findPictureById(Long id) {
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT filename, datatype, data FROM pictures WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            PictureDTO pictureDTO = new PictureDTO(rs.getString("filename"),
                    rs.getString("datatype"),
                    rs.getBinaryStream("data"));
            rs.close();
            ps.close();
            c.close();
            return pictureDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    void insert(PictureUploadDTO pictureUploadDTO) {
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO pictures(filename, datatype, description, data) VALUES (?, ?, ?, ?)");
            String filename = pictureUploadDTO.getFileName();
            ps.setString(1, filename.substring(0, filename.lastIndexOf(".")));
            ps.setString(2, filename.substring(filename.lastIndexOf(".") + 1));
            ps.setString(3, pictureUploadDTO.getDescription());
            ps.setBinaryStream(4, pictureUploadDTO.getFile());
            ps.executeUpdate();
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
