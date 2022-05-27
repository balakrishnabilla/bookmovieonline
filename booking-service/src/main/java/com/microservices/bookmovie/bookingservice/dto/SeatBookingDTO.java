package com.microservices.bookmovie.bookingservice.dto;

import java.util.List;

public class SeatBookingDTO {

  private int showId;
  private List<String> selectedSeats;
  private int userId;

  public SeatBookingDTO() {}

  public SeatBookingDTO(int showId, List<String> selectedSeats, int userId) {
    super();
    this.showId = showId;
    this.selectedSeats = selectedSeats;
    this.userId = userId;
  }

  public int getShowId() {
    return showId;
  }

  public void setShowId(int showId) {
    this.showId = showId;
  }

  public List<String> getSelectedSeats() {
    return selectedSeats;
  }

  public void setSelectedSeats(List<String> selectedSeats) {
    this.selectedSeats = selectedSeats;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
