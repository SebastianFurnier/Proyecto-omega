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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/login/**","/api/employee/register/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(serviceUserDetailsImp)
                .exceptionHandling(e->e.accessDeniedHandler(customAccessDanieHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                .sessionManagement(session-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
