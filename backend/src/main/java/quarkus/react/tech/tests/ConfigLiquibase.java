package quarkus.react.tech.tests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import liquibase.command.CommandScope;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ConfigLiquibase {
    @Inject
    ConfigDataSource dataSource;

    @ConfigProperty(name = "application.liquibase.change-log")
    String changeLog;

    @ConfigProperty(name = "application.liquibase.enabled")
    boolean enabled;

    public void runLiquibase() throws Exception {
        if (enabled) {
            new CommandScope("update")
                    .addArgumentValue(
                            DbUrlConnectionCommandStep.DATABASE_ARG,
                            DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection())))
                    .addArgumentValue("changeLogFile", changeLog)
                    .addArgumentValue("searchPath", new ClassLoaderResourceAccessor(ConfigLiquibase.class.getClassLoader()))
                    .execute();
        }
    }

}
