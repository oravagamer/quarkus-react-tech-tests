package quarkus.react.tech.tests;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class QuarkusReactApplicationMain {
    public static void main(String... args) {
        Quarkus.run(QuarkusReactApplication.class, args);
    }

    public static class QuarkusReactApplication implements QuarkusApplication {
        @Inject
        ConfigLiquibase liquibase;

        @Override
        public int run(String... args) throws Exception {
            liquibase.runLiquibase();
            Quarkus.waitForExit();
            return 0;
        }
    }
}
