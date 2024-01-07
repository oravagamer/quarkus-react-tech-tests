package quarkus.react.tech.tests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.sql.*;
import java.util.*;

/**
 * v1.0.0
 * Don't use with {@code InputStream} or {@code OutputStream} or {@code Array}
 *
 * @see java.io.InputStream
 * @see java.io.OutputStream
 */

@ApplicationScoped
public class DbDAO {
    @Inject
    ConfigDataSource dataSource;

    Logger logger = Logger.getLogger(DbDAO.class);

    /**
     * For {@code INSERT} or {@code UPDATE} or {@code DELETE}
     *
     * @param sql  is basic SQL query with ? for parameter placeholder
     * @param args is nullable array with params injected into sql query
     */
    public void createUpdateDelete(String sql, Object... args) {
        Connection c = dataSource.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            preparedStatementMap(ps, args);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        }
    }

    /**
     * For {@code SELECT}
     *
     * @param sql  is basic SQL query with ? for parameter placeholder
     * @param args is nullable array with params injected into sql query
     * @return {@code ArrayList<HashMap<String, Object>>} list full of HashMap objects that contains returned values under column name
     */

    public ArrayList<HashMap<String, Object>> read(String sql, Object... args) {
        Connection c = dataSource.getConnection();
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            preparedStatementMap(ps, args);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> hm = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    switch (rsmd.getColumnType(i)) {
                        case Types.BIGINT:
                            hm.put(rsmd.getColumnName(i), rs.getLong(i));
                            break;
                        case Types.TIMESTAMP:
                            hm.put(rsmd.getColumnName(i), rs.getTimestamp(i));
                            break;
                        case Types.VARCHAR:
                            hm.put(rsmd.getColumnName(i), rs.getString(i));
                            break;
                    }

                }
                result.add(hm);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        }
        return result;
    }


    void preparedStatementMap(PreparedStatement ps, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                ps.setNull(i + 1, Types.NULL);
            } else if (args[i].getClass().isNestmateOf(Long.class)) {
                ps.setLong(i + 1, ((Long) args[i]));
            } else if (args[i].getClass().isNestmateOf(String.class)) {
                ps.setString(i + 1, ((String) args[i]));
            }
        }
    }
}
