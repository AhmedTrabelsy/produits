package com.iset.produits.security;

class UserForm {
  private String username;
  private String password;
  private String confirmedPassword;
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getConfirmedPassword() {
    return confirmedPassword;
  }
  public void setConfirmedPassword(String confirmedPassword) {
    this.confirmedPassword = confirmedPassword;
  }
}
