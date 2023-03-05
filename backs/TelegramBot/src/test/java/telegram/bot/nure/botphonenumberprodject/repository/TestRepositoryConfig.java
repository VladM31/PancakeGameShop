package telegram.bot.nure.botphonenumberprodject.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@TestConfiguration
public class TestRepositoryConfig {

    @Bean
    DataBasePut userFiller(UserRepository repository){
        return new UserPut(repository);
    }

    @Bean
    FlywayMigrateResult sqliteTempDataBase(DataSource dataSource, List<DataBasePut> puts){
        return new FlywayMigrateResult(dataSource,puts);
    }


}
