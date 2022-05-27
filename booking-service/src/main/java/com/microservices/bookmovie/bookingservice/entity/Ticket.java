package com.microservices.bookmovie.bookingservice.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Ticket {

  @Id @GeneratedValue private Integer ticketId;

  private int showId;
  private int userId;
  private double totalCost;
  private java.time.LocalDateTime bookingTime;
  private String seatNumbers;

  public Ticket() {}

  public Ticket(
      int showId,
      int userId,
      double totalCost,
      LocalDateTime bookingTime,
      String seatNumbers) {
    super();
    this.showId = showId;
    this.userId = userId;
    this.totalCost = totalCost;
    this.bookingTime = bookingTime;
    this.seatNumbers = seatNumbers;
  }
}
