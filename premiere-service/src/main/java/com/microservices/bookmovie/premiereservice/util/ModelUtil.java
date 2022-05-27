package com.microservices.bookmovie.premiereservice.util;

import com.microservices.bookmovie.premiereservice.dto.SeatDTO;
import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.entity.Seat;
import com.microservices.bookmovie.premiereservice.entity.Show;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ModelUtil {

  public SeatDTO convertEntityToDTO(Seat seat) {
    return new SeatDTO(
        seat.getSeatId(), seat.getShowId(), seat.getSeatNum(), seat.getStatus(), seat.getPrice());
  }

    public static ShowDTO convertEntityToDTO(Show show) {
      return   new ShowDTO(
                show.getShowId(),
                show.getTheatreId(),
                show.getShowDate().toString(),
                show.getShowTime(),
                show.getTotalSeats(),
                show.getAvailableSeats());
    }

  public static Show convertDTOtoEntity(ShowDTO showDTO) {

    return new Show(
        showDTO.getShowId(),
        showDTO.getTheatreId(),
        LocalDate.parse(showDTO.getShowDate()),
        showDTO.getStatus(),
        showDTO.getTotalSeats(),
        showDTO.getAvailableSeats());
  }

  public static List<ShowDTO> convertShowDTOs(List<Show> shows) {
    List<ShowDTO> showDTOList =
        shows.stream()
            .map(
                show ->
                    new ShowDTO(
                        show.getShowId(),
                        show.getTheatreId(),
                        show.getShowDate().toString(),
                        show.getShowTime(),
                        show.getTotalSeats(),
                        show.getAvailableSeats()))
            .collect(Collectors.toList());
    return showDTOList;
  }
}
