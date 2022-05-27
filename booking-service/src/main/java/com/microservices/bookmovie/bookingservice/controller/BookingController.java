package com.microservices.bookmovie.bookingservice.controller;

import com.microservices.bookmovie.bookingservice.config.PremiereServiceProxy;
import com.microservices.bookmovie.bookingservice.dto.BookingDetailsDTO;
import com.microservices.bookmovie.bookingservice.dto.SeatBookingDTO;
import com.microservices.bookmovie.bookingservice.entity.Ticket;
import com.microservices.bookmovie.bookingservice.exception.SeatNotAvailableException;
import com.microservices.bookmovie.bookingservice.repo.BookingServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-service")
public class BookingController {

  @Autowired private PremiereServiceProxy proxy;

  @Autowired private BookingServiceRepository repo;

  @PostMapping("/book")
  public Ticket confirmSeats(@RequestBody SeatBookingDTO seatBookingDTO) throws Exception {
    // lock the seats
    try {
      proxy.lockSeats(seatBookingDTO);
    } catch (Exception ex) {
      throw new SeatNotAvailableException(ex);
    }
    // payment gateway 3rd party
    // confirm seats
    BookingDetailsDTO bookingDetailsDTO = proxy.bookSeats(seatBookingDTO);
    Ticket ticket =
        new Ticket(
            seatBookingDTO.getShowId(),
            seatBookingDTO.getUserId(),
            bookingDetailsDTO.getTotalCost(),
            bookingDetailsDTO.getBookingTime(),
            bookingDetailsDTO.getSeatNumbers());
    repo.save(ticket);
    return ticket;
  }
}
