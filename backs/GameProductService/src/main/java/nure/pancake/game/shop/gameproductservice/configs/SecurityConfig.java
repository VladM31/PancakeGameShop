package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.dataobjects.Role;
import nure.pancake.game.shop.gameproductservice.security.JwtAuthFilter;
import nure.pancake.game.shop.gameproductservice.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthService authService,
                                           @Value("${token-prefix}") String tokenPrefix) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/bought-content/**","/games/{gameId}/file","/promo-code/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/genres")
                .hasAuthority(Role.ADMINISTRATION.name())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(tokenPrefix, authService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
