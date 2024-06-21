package dev.example.kinect.securite;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(ar -> ar.requestMatchers("/", "/oauth2Login/**", "/webjars/**", "/h2-console/**").permitAll())
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults() )
                .build();
    }
}