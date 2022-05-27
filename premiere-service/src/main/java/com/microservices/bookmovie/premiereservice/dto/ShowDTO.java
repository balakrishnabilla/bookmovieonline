package com.microservices.bookmovie.premiereservice.dto;

public class ShowDTO {
  private Long showId;

  private int theatreId;

  private String showDate;
  private String showTime;
  private String status;
  private int totalSeats;
  private int availableSeats;

  public ShowDTO() {}

  public ShowDTO(
      Long showId,
      int theatreId,
      String showDate,
      String showTime,
      int totalSeats,
      int availableSeats) {
    super();
    this.showId = showId;
    this.theatreId = theatreId;
    this.showTime = showTime;
    this.showDate = showDate;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
    this.status = getStatus();
  }

  public String getStatus() {
    double diff = this.totalSeats - this.availableSeats;
    double available = 1 - (diff / this.totalSeats);
    if (available > 0.75 & available < 1.0) {
      return Status.ALMOST_FULL.toString();
    } else if (available == 0.0) {
      return Status.HOUSE_FULL.toString();
    }
    return Status.AVAILABLE.toString();
  }

  public Long getShowId() {
    return showId;
  }

  public int getTheatreId() {
    return theatreId;
  }

  public String getShowDate() {
    return showDate;
  }

  public String getShowTime() {
    return showTime;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setShowId(Long showId) {
    this.showId = showId;
  }

  public void setTheatreId(int theatreId) {
    this.theatreId = theatreId;
  }

  public void setShowDate(String showDate) {
    this.showDate = showDate;
  }

  public void setShowTime(String showTime) {
    this.showTime = showTime;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setTotalSeats(int totalSeats) {
    this.totalSeats = totalSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }
}

enum Status {
  ALMOST_FULL("Almost Full"),
  HOUSE_FULL("House Full"),
  AVAILABLE("Available");
  String status;

  Status(String s) {
    this.status = s;
  }

  @Override
  public String toString() {
    return this.status;
  }
}
