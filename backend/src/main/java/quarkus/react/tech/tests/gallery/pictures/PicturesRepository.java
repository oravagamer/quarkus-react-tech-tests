package quarkus.react.tech.tests.gallery.pictures;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequestScoped
public class PicturesRepository implements PicturesDAO {
    @Inject
    Instance<Connection> connection;

    Logger logger = Logger.getLogger(PicturesRepository.class);

    @Override
    public PictureDTO findPictureById(Long id) {
        try {
            Connection c = connection.get();
            PreparedStatement ps = c.prepareStatement("SELECT filename, datatype, data FROM pictures WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            PictureDTO pictureDTO = new PictureDTO(rs.getString("filename"),
                    rs.getString("datatype"),
                    rs.getBinaryStream("data"));
            return pictureDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Picture findById(Long id) {
        return null;
    }

    @Override
    public void update(Picture entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void insert(Picture entity) {

    }

    @PreDestroy
    @Override
    public void closeConnection() {
        try {
            this.connection.get().close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
