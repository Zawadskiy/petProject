package com.example.petproject.config.security;

import com.example.petproject.config.authentication.AuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    private final ApplicationContext context;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, ApplicationContext context) {
        this.userDetailsService = userDetailsService;
        this.context = context;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers("/auth/signup").permitAll()
//                .requestMatchers("/**").authenticated()
                .requestMatchers("/**").permitAll()

                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authenticationFilterChain(HttpSecurity http, AuthenticationManager authenticationManager,
                                                         ObjectMapper objectMapper)
//            todo: можно было throws и не переносить, как по мне
            throws Exception {

        http.securityMatcher("/auth/signin")
                .authorizeHttpRequests()
                .requestMatchers("/auth/signin").permitAll();
//                .and().formLogin()
//                .loginPage("/auth/signin");

        http.csrf().disable();

        http.addFilterAfter(new AuthenticationFilter(authenticationManager, objectMapper), BasicAuthenticationFilter.class);

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
