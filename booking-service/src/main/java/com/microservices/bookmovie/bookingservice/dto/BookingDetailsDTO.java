package com.microservices.bookmovie.bookingservice.dto;

import java.time.LocalDateTime;

public class BookingDetailsDTO {

  private int showId;
  private double totalCost;
  private LocalDateTime bookingTime;
  private String seatNumbers;

  public BookingDetailsDTO(
      int showId, double totalCost, LocalDateTime bookingTime, String numberOfTickets) {
    super();
    this.showId = showId;
    this.totalCost = totalCost;
    this.bookingTime = bookingTime;
    this.seatNumbers = numberOfTickets;
  }

  public int getShowId() {
    return showId;
  }

  public void setShowId(int showId) {
    this.showId = showId;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public LocalDateTime getBookingTime() {
    return bookingTime;
  }

  public void setBookingTime(LocalDateTime bookingTime) {
    this.bookingTime = bookingTime;
  }

  public String getSeatNumbers() {
    return seatNumbers;
  }

  public void setSeatNumbers(String seatNumbers) {
    this.seatNumbers = seatNumbers;
  }
}
