package hibernatecrudservice.services;

import hibernatecrudservice.DatabaseException;
import hibernatecrudservice.Properties;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class DatabaseInitService {
    public static void init() throws DatabaseException {
        try {
            Properties properties = new Properties("properties.json");
            Flyway flyway = Flyway.configure().baselineOnMigrate(true).baselineVersion("0").dataSource(
                    properties.getUrl(),
                    properties.getUsername(),
                    properties.getPassword()
            ).load();
            flyway.migrate();
        } catch (FlywayException e) {
            throw new DatabaseException("Flyway error: " + e.getMessage(), e);
        }
    }

    private DatabaseInitService() {}
}
