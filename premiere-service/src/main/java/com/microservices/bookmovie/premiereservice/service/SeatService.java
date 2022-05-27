package com.microservices.bookmovie.premiereservice.service;

import com.microservices.bookmovie.premiereservice.constants.SeatStatus;
import com.microservices.bookmovie.premiereservice.dto.BookingDetailsDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatInfoDTO;
import com.microservices.bookmovie.premiereservice.entity.Seat;
import com.microservices.bookmovie.premiereservice.entity.Show;
import com.microservices.bookmovie.premiereservice.exception.SeatNotAvailableException;
import com.microservices.bookmovie.premiereservice.repo.SeatRepository;
import com.microservices.bookmovie.premiereservice.repo.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
  private SeatRepository seatRepository;
  private ShowRepository showRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository, ShowRepository showRepository) {
    this.seatRepository = seatRepository;
    this.showRepository = showRepository;
  }

  public List<SeatDTO> getSeats(int showId) {
    List<Seat> seats =
        seatRepository.findByShowIdAndStatus(showId, SeatStatus.AVAILABLE.getValue());
    return seats.stream().map(seat -> convertEntityToDTO(seat)).collect(Collectors.toList());
  }

  private SeatDTO convertEntityToDTO(Seat seat) {
    return new SeatDTO(
        seat.getSeatId(), seat.getShowId(), seat.getSeatNum(), seat.getStatus(), seat.getPrice());
  }

  public BookingDetailsDTO bookSeats(SeatInfoDTO seatInfoDTO) {

    List<Seat> selectedSeats =
        seatRepository.findByShowIdAndSeatNumIn(
            seatInfoDTO.getShowId(), seatInfoDTO.getSelectedSeats());

    selectedSeats.stream()
        .iterator()
        .forEachRemaining(
            seat -> {
              seat.setStatus(SeatStatus.BOOKED.getValue());
              seatRepository.save(seat);
            });

    String seatNumbers =
        selectedSeats.stream().map(Seat::getSeatNum).collect(Collectors.joining(","));
    double totalCost = selectedSeats.stream().mapToDouble(seat -> seat.getPrice()).sum();

    Show show = showRepository.findById((long) seatInfoDTO.getShowId()).get();
    show.setAvailableSeats(show.getAvailableSeats() - 1);
    return new BookingDetailsDTO(
        seatInfoDTO.getShowId(), totalCost, java.time.LocalDateTime.now(), seatNumbers);
  }

  public void lockSeats(SeatInfoDTO seatInfoDTO) throws SeatNotAvailableException {
    List<Seat> selectedSeats =
        seatRepository.findByShowIdAndSeatNumIn(
            seatInfoDTO.getShowId(), seatInfoDTO.getSelectedSeats());

    if (selectedSeats.isEmpty()) {
      throw new SeatNotAvailableException("Seats not available");
    }

    if (selectedSeats.stream().anyMatch(seat -> seat.getStatus() == SeatStatus.BOOKED.getValue())) {
      String bookedSeats =
          selectedSeats.stream()
              .filter(seat -> seat.getStatus() == SeatStatus.BOOKED.getValue())
              .map(Seat::getSeatNum)
              .collect(Collectors.joining(","));
      throw new SeatNotAvailableException(bookedSeats);
    }

    selectedSeats.stream()
        .iterator()
        .forEachRemaining(
            seat -> {
              seat.setStatus(SeatStatus.LOCKED.getValue());
              seatRepository.save(seat);
            });
  }
}
