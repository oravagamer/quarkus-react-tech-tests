package quarkus.react.tech.tests;

import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class ConfigDataSource {
    private static final Logger logger = Logger.getLogger(ConfigDataSource.class);

    @ConfigProperty(name = "application.datasource.username")
    String username;

    @ConfigProperty(name = "application.datasource.password")
    String password;

    @ConfigProperty(name = "application.datasource.jdbc-url")
    String url;

    @Dependent
    @Produces
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

}
