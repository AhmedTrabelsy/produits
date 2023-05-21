package com.iset.produits.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.iset.produits.service.UserService;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,
// jsr250Enabled = true)
public class SecurityConfig {
  public static final String AUTHORITIES_CLAIM_NAME = "roles";
  @Autowired
  UserService userDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf().disable()
        .authenticationProvider(getProvider())
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/ListeProduits", true)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/logout")
        .invalidateHttpSession(true)
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/webjars/**").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/logout").permitAll()
        .requestMatchers("/ajouterCompte").permitAll()
        .requestMatchers("/saveUser").permitAll()
        .anyRequest().authenticated();

    http.exceptionHandling().accessDeniedPage("/accessDenied");
    return http.build();
  }

  @Bean
  public AuthenticationProvider getProvider() {
    AppAuthProvider provider = new AppAuthProvider();
    System.out.println("here is the provider calling");
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}