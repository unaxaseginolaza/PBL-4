package com.example.pbl4.config;

import com.example.pbl4.filter.RoleBasedEmployeeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/*", "/index", "/css/*", "/script/*", "/image/*", "/icon/*", "/user/*", "/employee/*", "/employee/*/*",
                                "/customer/*", "/customer/*/*","/templates/*", "/login/*", "/login","/preProcessedMaterial/*",
                                "/preProcessedMaterial/*/*", "/type/*", "/type/*/*", "/processedMaterial/*", "/processedMaterial/*/*",
                                "/section/*", "section/*/*", "/task/*", "/task/*/*", "/supplier/*", "/supplier/*/*",  "/purchase/*", "/purchase/*/*",
                                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css",
                                "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js").permitAll() // Permitir acceso sin autenticación a estas rutas
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .csrf(AbstractHttpConfigurer::disable);// Deshabilitar CSRF si estás manejando los tokens tú mismo.
        return http.build();
    }
}
