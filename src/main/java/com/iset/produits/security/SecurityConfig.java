package com.iset.produits.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity
public class SecurityConfig {
  @Bean
  public UserDetailsService users() {
    PasswordEncoder passwordEncoder = passwordEncoder();
    UserDetails user = User.builder().username("Najla").password(passwordEncoder.encode("123")).roles("AGENT").build();
    UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("123")).roles("ADMIN").build();
    UserDetails user1 = User.builder().username("user1").password(passwordEncoder.encode("123")).roles("USER").build();

    return new InMemoryUserDetailsManager(user, admin, user1);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/showCreateProduit", "/saveProduit").hasAnyRole("ADMIN", "AGENT");
    http.authorizeHttpRequests()
        .requestMatchers("/showCreateCategorie", "/saveCategorie").hasAnyRole("ADMIN", "AGENT");
    http.authorizeHttpRequests()
        .requestMatchers("/ListeProduits", "ListeCategories").hasAnyRole("ADMIN", "AGENT", "USER");
    http.authorizeHttpRequests().requestMatchers("/supprimerProduit", "/modifierProduit",
        "/updateProduit").hasAnyRole("ADMIN");
    http.authorizeHttpRequests().requestMatchers("/supprimerCategorie", "/modifierCategorie",
        "/updateCategorie").hasAnyRole("ADMIN");

    http.authorizeHttpRequests().anyRequest().authenticated();
    http.exceptionHandling().accessDeniedPage("/accessDenied");
    http.formLogin();
    http.httpBasic();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

// // http.authorizeRequests().anyRequest().authenticated();

// http.authorizeRequests().antMatchers("/showCreateProduit",
// "/saveProduit").hasAnyRole("ADMIN", "AGENT");

// http.authorizeRequests().antMatchers("/ListeProduits").hasAnyRole("ADMIN",
// "AGENT", "USER");

// http.authorizeRequests().antMatchers("/supprimerProduit", "/modifierProduit",
// "/updateProduit").hasAnyRole("ADMIN");

// http.authorizeRequests().anyRequest().authenticated();
// http.exceptionHandling().accessDeniedPage("/accessDenied");
// http.formLogin();