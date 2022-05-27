package com.microservices.bookmovie.bookingservice.config;

import com.microservices.bookmovie.bookingservice.dto.BookingDetailsDTO;
import com.microservices.bookmovie.bookingservice.dto.SeatBookingDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "premiere-service")
@RibbonClient(name = "premiere-service")
public interface PremiereServiceProxy {
  @PostMapping("/premiere-service/bookseats")
  public BookingDetailsDTO bookSeats(SeatBookingDTO seatBookingDTO);

  @PostMapping("/premiere-service/lockseats")
  public void lockSeats(SeatBookingDTO seatBookingDTO) throws Exception;

  @PostMapping("/premiere-service/getseats")
  public void getseats();


}
