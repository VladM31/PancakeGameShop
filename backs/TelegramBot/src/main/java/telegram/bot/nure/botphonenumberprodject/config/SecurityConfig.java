package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import telegram.bot.nure.botphonenumberprodject.service.ApiAuthenticationService;
import telegram.bot.nure.botphonenumberprodject.security.JWTAuthenticationFilter;
import telegram.bot.nure.botphonenumberprodject.service.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    ApiAuthenticationService tokenAuthenticationService(@Value("${security.secret.word}") String secret){
        return new TokenAuthenticationService(secret);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,ApiAuthenticationService authenticationService) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/rest/api/**").authenticated()
                .and()
                .addFilterBefore(new JWTAuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
