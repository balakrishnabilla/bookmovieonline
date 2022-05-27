package com.microservices.bookmovie.premiereservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.dto.TheatreDTO;
import com.microservices.bookmovie.premiereservice.entity.Show;
import com.microservices.bookmovie.premiereservice.entity.Theatre;
import com.microservices.bookmovie.premiereservice.repo.ShowRepository;
import com.microservices.bookmovie.premiereservice.repo.TheatreRepository;
import com.microservices.bookmovie.premiereservice.service.ShowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ShowServiceTest {
  public static final String BASE_URL = "/premiere-service";
  @MockBean private TheatreRepository theatreRepository;
  @MockBean private ShowRepository showRepository;
  private ShowService showService;
  private static final ObjectMapper mapper = new ObjectMapper();

  @Before
  public void setUp() {
    showService = new ShowService(theatreRepository, showRepository);
  }

  @Test
  public void contextLoads() {}

  @Test
  public void testGetShowsByCityNMovieNDate() throws Exception {
    // input
    List<Theatre> list = new ArrayList<>();
    List<Show> shows = new ArrayList<>();
    shows.add(new Show(3L, 1, LocalDate.of(2022, 05, 21), "11:30", 200, 120));
    list.add(new Theatre(1L, "Pheonix", 43, 123, shows));

    when(theatreRepository.findByCityIdAndMovieId(anyInt(), anyInt())).thenReturn(list);
    List<TheatreDTO> responseList = showService.getShows(43, 123, LocalDate.of(2022, 05, 21));
    assertEquals(1, responseList.size());
    assertEquals(43, responseList.get(0).getCityId());
    assertEquals(1, responseList.get(0).getShows().size());
    assertEquals("2022-05-21", responseList.get(0).getShows().get(0).getShowDate());
    assertEquals("11:30", responseList.get(0).getShows().get(0).getShowTime());
    assertEquals(new Long(3), responseList.get(0).getShows().get(0).getShowId());
    assertEquals(new Long(1), responseList.get(0).getTheatreId());
  }

  @Test
  public void testGetShowsByTheater() throws Exception {
    // input
    List<Show> shows = new ArrayList<>();
    shows.add(new Show(3L, 1, LocalDate.of(2022, 05, 21), "2:30", 140, 120));

    when(showRepository.findByTheatreId(anyInt())).thenReturn(shows);
    List<ShowDTO> responseList = showService.getShows(1);
    assertEquals(1, responseList.size());
    assertEquals("2022-05-21", responseList.get(0).getShowDate());
    assertEquals("2:30", responseList.get(0).getShowTime());
    assertEquals(new Long(3), responseList.get(0).getShowId());
    assertEquals(1, responseList.get(0).getTheatreId());
  }

  private String getJsonString(Object input) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(input);
    return requestJson;
  }
}
