package com.microservices.bookmovie.premiereservice.constants;

public enum SeatStatus {
  BOOKED(0),
  AVAILABLE(1),
  LOCKED(2),
  CANCELLED(3);
  int status;

  SeatStatus(int s) {
    this.status = s;
  }

  public int getValue() {
    return this.status;
  }
}
