package quarkus.react.tech.tests;

import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class JDBCRollback {
    static Logger logger = Logger.getLogger(JDBCRollback.class);

    public static void rollback(Connection c, Savepoint sp) {
        try {
            c.rollback(sp);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        try {
            c.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        try {
            c.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
