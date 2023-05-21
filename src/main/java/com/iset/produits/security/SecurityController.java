package com.iset.produits.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iset.produits.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
  @Autowired
  UserService userService;

  @GetMapping("/accessDenied")
  public String geterror() {
    return "accessDenied";
  }

  @PostMapping("/accessDenied")
  public String posterror() {
    return "accessDenied";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request) throws ServletException {
    request.logout();
    return "redirect:/login";
  }

  @GetMapping("/ajouterCompte")
  public String ajouterCompte() {
    return "createUser";
  }

  @PostMapping("/saveUser")
  public String saveUser(@ModelAttribute("userForm") UserForm userForm) {
    userService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    return "/login";
  }
}