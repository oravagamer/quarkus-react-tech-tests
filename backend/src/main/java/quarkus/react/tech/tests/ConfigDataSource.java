package quarkus.react.tech.tests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class ConfigDataSource {
    @Inject
    Logger logger;

    @ConfigProperty(name = "application.datasource.username")
    String username;

    @ConfigProperty(name = "application.datasource.password")
    String password;

    @ConfigProperty(name = "application.datasource.jdbc-url")
    String url;

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

}
