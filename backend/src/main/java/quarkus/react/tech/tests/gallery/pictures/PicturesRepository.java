package quarkus.react.tech.tests.gallery.pictures;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import quarkus.react.tech.tests.ConfigDataSource;
import quarkus.react.tech.tests.gallery.pictures.DTO.PictureDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class PicturesRepository implements PicturesDAO {
    @Inject
    ConfigDataSource datasource;

    @Override
    public PictureDTO findPictureById(Long id) {
        try {
            Connection c = datasource.getConnection();
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
}
