package com.microservices.bookmovie.premiereservice.controller;

import com.microservices.bookmovie.premiereservice.dto.BookingDetailsDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatInfoDTO;
import com.microservices.bookmovie.premiereservice.exception.SeatNotAvailableException;
import com.microservices.bookmovie.premiereservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/premiere-service")
public class SeatController {

  @Autowired SeatService seatService;

  @GetMapping("/seats")
  public @ResponseBody List<SeatDTO> getSeats(
      @NumberFormat(style = NumberFormat.Style.NUMBER) @RequestParam("showId") int showId) {
    return seatService.getSeats(showId);
  }

  @PostMapping("/lockseats")
  public void lockSeats(@RequestBody SeatInfoDTO seatInfoDTO) throws SeatNotAvailableException {
    seatService.lockSeats(seatInfoDTO);
  }

  @PostMapping("/bookseats")
  public BookingDetailsDTO bookSeats(@RequestBody SeatInfoDTO seatInfoDTO) {
    return seatService.bookSeats(seatInfoDTO);
  }
}
