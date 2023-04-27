package ru.expanse.medfuture.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriter;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class DefaultSecurityConfig {

    private final DataSource dataSource;
    @Value("${base.url}")
    private String baseUrl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http
//                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
                .csrf().disable()
                .authorizeHttpRequests((httpRequest) -> httpRequest
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/success").permitAll()
                        .requestMatchers("/").permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("http://" + baseUrl + ":8080/success"));
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.addHeaderWriter(new CustomHeaderWriter()));

//        http.cors().configurationSource(request -> corsConfiguration);
//        http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
        // ...
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user@mail.ru")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin@mail.ru")
                .password("password")
                .roles("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager service = new JdbcUserDetailsManager(dataSource);
        service.createUser(user);
        service.createUser(admin);

        return service;
    }
}

class CustomHeaderWriter implements HeaderWriter {

    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, PUT, OPTIONS, PATCH, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Private-Network", "true");
    }
}

//@Component
//class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//                       AccessDeniedException accessDeniedException) throws ServletException, IOException {
//        logger.debug(accessDeniedException.getMessage());
//        logger.debug("CSRFToken val: " + request.getHeader("X-XSRF-TOKEN"));
//
//        super.handle(request, response, accessDeniedException);
//    }
//}