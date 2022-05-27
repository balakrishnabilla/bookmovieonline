package com.microservices.bookmovie.premiereservice.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Show {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long showId;

  private int theatreId;

  private java.time.LocalDate showDate;
  private String showTime;
  private int totalSeats;
  private int availableSeats;

  public Show() {}

  public Show(
      Long showId,
      int theatreId,
      LocalDate showDate,
      String showTime,
      int totalSeats,
      int availableSeats) {
    super();
    this.showId = showId;
    this.theatreId = theatreId;
    this.showDate = showDate;
    this.showTime = showTime;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
  }

  public Long getShowId() {
    return showId;
  }

  public void setShowId(Long showId) {
    this.showId = showId;
  }

  public int getTheatreId() {
    return theatreId;
  }

  public void setTheatreId(int theatreId) {
    this.theatreId = theatreId;
  }

  public LocalDate getShowDate() {
    return showDate;
  }

  public void setShowDate(LocalDate showDate) {
    this.showDate = showDate;
  }

  public String getShowTime() {
    return showTime;
  }

  public void setShowTime(String showTime) {
    this.showTime = showTime;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public void setTotalSeats(int totalSeats) {
    this.totalSeats = totalSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }
}
