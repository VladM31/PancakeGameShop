package nure.pancake.game.shop.authorizationservice.configs;

import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.httpfilter.JwtAuthFilter;
import nure.pancake.game.shop.authorizationservice.services.TokenService;
import nure.pancake.game.shop.authorizationservice.services.UserService;
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
                                           UserService userService,
                                           TokenService tokenService,
                                           @Value("${token-prefix}")
                                                   String tokenPrefix) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/users").hasAuthority(Role.ADMINISTRATION.name())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(tokenService, userService, tokenPrefix),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}