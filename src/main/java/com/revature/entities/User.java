package com.revature.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {
  @Id
  @Column(name = "email")
  private String email;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "password")
  private String password; // might want to encrypt this at some point

  @OneToMany
  private List<Pin> userPins;
  /*
  potential add-ons:
  payment details
  name
   */

  public User() {}

  public User(String email, String displayName, String password, List<Pin> userPins) {
    this.email = email;
    this.displayName = displayName;
    this.password = password;
    this.userPins = userPins;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Pin> getUserPins() {
    return userPins;
  }

  public void setUserPins(List<Pin> userPins) {
    this.userPins = userPins;
  }

  @Override
  public String toString() {
    return "com.revature.entities.User{" +
      "email='" + email + '\'' +
      ", displayName='" + displayName + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
