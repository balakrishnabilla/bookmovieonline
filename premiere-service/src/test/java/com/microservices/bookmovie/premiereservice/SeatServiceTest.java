package com.microservices.bookmovie.premiereservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.microservices.bookmovie.premiereservice.constants.SeatStatus;
import com.microservices.bookmovie.premiereservice.dto.BookingDetailsDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatInfoDTO;
import com.microservices.bookmovie.premiereservice.entity.Seat;
import com.microservices.bookmovie.premiereservice.entity.Show;
import com.microservices.bookmovie.premiereservice.repo.SeatRepository;
import com.microservices.bookmovie.premiereservice.repo.ShowRepository;
import com.microservices.bookmovie.premiereservice.service.SeatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SeatServiceTest {
  public static final String BASE_URL = "/premiere-service";
  @MockBean private SeatRepository seatRepository;
  @MockBean private ShowRepository showRepository;
  private SeatService seatService;
  private static final ObjectMapper mapper = new ObjectMapper();

  @Before
  public void setUp() {
    seatService = new SeatService(seatRepository, showRepository);
  }

  @Test
  public void contextLoads() {}

  @Test
  public void testLockSeats() throws Exception {
    // input
    List<Seat> seats = new ArrayList<>();

    seats.add(new Seat(1L, 2, "a1", SeatStatus.AVAILABLE.getValue(), 100.0));

    when(seatRepository.findByShowIdAndStatus(anyInt(), anyInt())).thenReturn(seats);
    List<SeatDTO> responseList = seatService.getSeats(2);
    assertEquals(1, responseList.size());
    assertEquals(new Long(1), responseList.get(0).getSeatId());
    assertEquals(2, responseList.get(0).getShowId());
    assertEquals("a1", responseList.get(0).getSeatNum());
    assertEquals(SeatStatus.AVAILABLE.getValue(), responseList.get(0).getStatus());
    assertEquals(100.0, responseList.get(0).getPrice());
  }

  @Test
  public void testBookSeats() throws Exception {
    // input

    SeatInfoDTO seat = new SeatInfoDTO(1, Arrays.asList("a1"), SeatStatus.AVAILABLE.getValue());
    List<Seat> seats = new ArrayList<>();
    seats.add(new Seat(1L, 2, "a1", SeatStatus.AVAILABLE.getValue(), 100.0));

    Optional<Show> show =
        Optional.of(new Show(2L, 1, LocalDate.of(2022, 05, 21), "11:30", 200, 120));
    when(seatRepository.findByShowIdAndSeatNumIn(anyInt(), any())).thenReturn(seats);
    when(showRepository.findById(any())).thenReturn(show);

    BookingDetailsDTO response = seatService.bookSeats(seat);
    assertEquals(1, response.getShowId());
    assertEquals(100.0, response.getTotalCost());
    assertEquals("a1", response.getSeatNumbers());
  }

  private String getJsonString(Object input) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(input);
    return requestJson;
  }
}
