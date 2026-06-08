package com.example.trabalha_paulista.config;

import com.example.trabalha_paulista.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final boolean jwtProtectionEnabled;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            @Value("${security.jwt.protection-enabled:true}") boolean jwtProtectionEnabled) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtProtectionEnabled = jwtProtectionEnabled;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    if (!jwtProtectionEnabled) {
                        auth.anyRequest().permitAll();
                        return;
                    }

                    auth
                            .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/status").permitAll()
                            .requestMatchers(
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/v3/api-docs/**")
                            .permitAll()
                            .requestMatchers("/auth/usuarios", "/auth/usuarios/**").authenticated()
                            .requestMatchers(
                                    "/inscricoes-vagas",
                                    "/inscricoes-vagas/**",
                                    "/inscricoes-cursos",
                                    "/inscricoes-cursos/**",
                                    "/inscricoes-mentorias",
                                    "/inscricoes-mentorias/**")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST,
                                    "/vagas",
                                    "/vagas/**",
                                    "/cursos",
                                    "/cursos/**",
                                    "/servicos",
                                    "/servicos/**",
                                    "/mentorias",
                                    "/mentorias/**",
                                    "/parcerias",
                                    "/parcerias/**")
                            .authenticated()
                            .requestMatchers(HttpMethod.PUT,
                                    "/vagas",
                                    "/vagas/**",
                                    "/cursos",
                                    "/cursos/**",
                                    "/servicos",
                                    "/servicos/**",
                                    "/mentorias",
                                    "/mentorias/**",
                                    "/parcerias",
                                    "/parcerias/**")
                            .authenticated()
                            .requestMatchers(HttpMethod.DELETE,
                                    "/vagas",
                                    "/vagas/**",
                                    "/cursos",
                                    "/cursos/**",
                                    "/servicos",
                                    "/servicos/**",
                                    "/mentorias",
                                    "/mentorias/**",
                                    "/parcerias",
                                    "/parcerias/**")
                            .authenticated()
                            .anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Permitir qualquer origem para desenvolvimento
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
