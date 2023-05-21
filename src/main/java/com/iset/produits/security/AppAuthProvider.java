package com.iset.produits.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iset.produits.service.UserService;

public class AppAuthProvider extends DaoAuthenticationProvider {
  @Autowired
  UserService userDetailsService;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
    UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
    String name = auth.getName();
    String password = auth.getCredentials().toString();
    UserDetails user = userDetailsService.loadUserByUsername(name);
    System.out.println("here is the verification !" + password + " equals to " + user.getPassword());
    if (passwordEncoder.matches(password, user.getPassword())) {
      return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    } else {
      throw new BadCredentialsException("Invalid username/password");
    }
    // if (user == null) {
    // throw new BadCredentialsException("Username/Password does not match for "
    // + auth.getPrincipal());
    // }
    // return new UsernamePasswordAuthenticationToken(user, password,
    // user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }

}