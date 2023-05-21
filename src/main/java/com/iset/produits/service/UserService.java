package com.iset.produits.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iset.produits.dao.RoleRepository;
import com.iset.produits.dao.UserRepository;
import com.iset.produits.entities.Role;
import com.iset.produits.entities.User;

import groovy.util.logging.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Objects.requireNonNull(username);
    User user = userRepository.findUserWithName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return user;
  }

  public User saveUser(String username, String password, String confirmedPassword) {
    User appUser = new User();
    if (userRepository.findUserWithName(username).isPresent() == true)
      throw new RuntimeException("User already exists");
    if (!password.equals(confirmedPassword))
      throw new RuntimeException("Please confirm your password");
    appUser.setUsername(username);
    Set<Role> roles = new HashSet<Role>();
    Role r = new Role("ROLE_USER");
    roleRepository.save(r);
    roles.add(r);
    appUser.setRoles(roles);
    appUser.setPassword(new BCryptPasswordEncoder().encode(password));
    userRepository.save(appUser);
    return appUser;
  }
}