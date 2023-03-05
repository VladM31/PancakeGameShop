package telegram.bot.nure.botphonenumberprodject.repository;

import lombok.Data;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.List;


public class FlywayMigrateResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayMigrateResult.class);
    private MigrateResult migrateResult;

    public FlywayMigrateResult(DataSource dataSource,List<DataBasePut> puts) {
        this.migrate(dataSource,puts);
    }

    private void migrate(DataSource dataSource, List<DataBasePut> puts) {
        this.migrateResult = Flyway
                .configure()
                .dataSource(dataSource)
                .load()
                .migrate();

        LOGGER.debug("Migrate result. State success = " + migrateResult.success);
        if(migrateResult.success){
            puts.forEach(DataBasePut::put);
        }
    }

    public MigrateResult getMigrateResult() {
        return migrateResult;
    }
}
