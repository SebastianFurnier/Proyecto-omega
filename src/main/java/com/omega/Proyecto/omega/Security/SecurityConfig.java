package com.omega.Proyecto.omega.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ServiceUserDetailsImp serviceUserDetailsImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomAccessDanieHandler customAccessDanieHandler;

    public SecurityConfig(ServiceUserDetailsImp serviceUserDetailsImp, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccessDanieHandler customAccessDanieHandler) {
        this.serviceUserDetailsImp = serviceUserDetailsImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAccessDanieHandler = customAccessDanieHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/api/employee/login/**")
                                .permitAll()
                                .requestMatchers("/api/employee/getByUsername/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/doc/**")
                                .permitAll()
                                .requestMatchers("/v3/api-docs")
                                .permitAll()
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/v3/api-docs/swagger-config")
                                .permitAll()
                                .requestMatchers("/api/client/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/employee/**").hasAnyAuthority("ADMIN")
                                .requestMatchers("/api/package/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/sale/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/service/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(serviceUserDetailsImp)
                .exceptionHandling(e -> e.accessDeniedHandler(customAccessDanieHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
