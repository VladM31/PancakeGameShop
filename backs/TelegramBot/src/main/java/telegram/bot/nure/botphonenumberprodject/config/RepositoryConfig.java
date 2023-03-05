package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import telegram.bot.nure.botphonenumberprodject.repository.JdbcTemplateUserRepository;
import telegram.bot.nure.botphonenumberprodject.repository.UserRepository;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository jdbcTemplateUserRepository(JdbcTemplate jdbc,
                                                     NamedParameterJdbcTemplate namedJdbc){
        return  new JdbcTemplateUserRepository(namedJdbc, jdbc);
    }
}
