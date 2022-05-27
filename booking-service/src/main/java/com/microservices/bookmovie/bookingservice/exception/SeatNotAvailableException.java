package com.microservices.bookmovie.bookingservice.exception;

public class SeatNotAvailableException extends Exception {
  public SeatNotAvailableException(String message) {
    super(message);
  }

  public SeatNotAvailableException(Throwable message) {
    super(message);
  }
}
