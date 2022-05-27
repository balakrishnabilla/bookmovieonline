package com.microservices.bookmovie.premiereservice.dto;

public class SeatDTO {

  private Long seatId;

  private int showId;
  private String seatNum;
  private int status;
  private double price;

  public SeatDTO() {}

  public SeatDTO(Long seatId, int showId, String seatNum, int status, double price) {
    super();
    this.seatId = seatId;
    this.showId = showId;
    this.seatNum = seatNum;
    this.status = status;
    this.price = price;
  }

  public Long getSeatId() {
    return seatId;
  }

  public void setSeatId(Long seatId) {
    this.seatId = seatId;
  }

  public int getShowId() {
    return showId;
  }

  public void setShowId(int showId) {
    this.showId = showId;
  }

  public String getSeatNum() {
    return seatNum;
  }

  public void setSeatNum(String seatNum) {
    this.seatNum = seatNum;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
