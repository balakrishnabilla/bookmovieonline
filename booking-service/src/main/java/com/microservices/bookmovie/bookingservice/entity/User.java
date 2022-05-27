package com.microservices.bookmovie.bookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
  @Id @GeneratedValue
  private int userId;
  private String userName;
  private String phoneNum;
  private String email;

  public User(int userId, String userName, String phoneNum, String email) {
    this.userId = userId;
    this.userName = userName;
    this.phoneNum = phoneNum;
    this.email = email;
  }

  public int getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public String getEmail() {
    return email;
  }
}
