package com.kh.testspring1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/sec_*").authenticated()
            .requestMatchers("/login", "/register", "/index").permitAll()
            .anyRequest().permitAll())
        .csrf(crf -> crf.disable())
        .formLogin(form -> form
            // .loginPage("/login")
            .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .deleteCookies("rememberMe", "jsessionId")
            .invalidateHttpSession(true))
        .rememberMe(rememberMe -> rememberMe
            .key("rememberMe")
            .tokenValiditySeconds(86400))
        .build();
  }

}
