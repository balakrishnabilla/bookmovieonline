package com.microservices.bookmovie.premiereservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.microservices.bookmovie.premiereservice.controller.ShowController;
import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.dto.TheatreDTO;
import com.microservices.bookmovie.premiereservice.service.ShowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShowController.class)
public class ShowControllerTest {
  public static final String BASE_URL = "/premiere-service/shows";
  @Autowired private MockMvc mockMvc;
  @MockBean private ShowService showService;
  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void contextLoads() {}

  @Test
  public void testGetShowsByCityNMovieNDate() throws Exception {
    // input
    List<TheatreDTO> list = new ArrayList<>();
    List<ShowDTO> shows = new ArrayList<>();
    shows.add(new ShowDTO(3L, 1, "2022-05-21", "11:30", 200, 120));
    list.add(new TheatreDTO(1L, "Pheonix", 43, 123, shows));

    when(showService.getShows(anyInt(), anyInt(), any())).thenReturn(list);

    MvcResult result =
        mockMvc
            .perform(get(BASE_URL + "?cityId=1&movieId=1001&date=2018-12-26"))
            .andExpect(status().isOk())
            .andReturn();
    List<TheatreDTO> jsonResponse =
        mapper.readValue(
            result.getResponse().getContentAsString(), new TypeReference<List<TheatreDTO>>() {});
    assertEquals(1, jsonResponse.size());
    assertEquals(43, jsonResponse.get(0).getCityId());
    assertEquals(1, jsonResponse.get(0).getShows().size());
    assertEquals("2022-05-21", jsonResponse.get(0).getShows().get(0).getShowDate());
    assertEquals("11:30", jsonResponse.get(0).getShows().get(0).getShowTime());
    assertEquals(new Long(3), jsonResponse.get(0).getShows().get(0).getShowId());
    assertEquals(new Long(1), jsonResponse.get(0).getTheatreId());
  }

  public void testGetShowsByTheaterId() throws Exception {
    // input
    List<ShowDTO> shows = new ArrayList<>();
    shows.add(new ShowDTO(1L, 1, "2022-05-21", "2:30", 150, 120));

    when(showService.getShows(anyInt())).thenReturn(shows);

    MvcResult result =
        mockMvc.perform(get(BASE_URL + "?theaterId=1")).andExpect(status().isOk()).andReturn();
    List<ShowDTO> jsonResponse =
        mapper.readValue(
            result.getResponse().getContentAsString(), new TypeReference<List<ShowDTO>>() {});
    assertEquals(1, jsonResponse.size());
    assertEquals("2022-05-21", jsonResponse.get(0).getShowDate());
    assertEquals("11:30", jsonResponse.get(0).getShowTime());
    assertEquals(new Long(3), jsonResponse.get(0).getShowId());
    assertEquals(1, jsonResponse.get(0).getTheatreId());
    assertEquals(120, jsonResponse.get(0).getAvailableSeats());
  }

  @Test
  public void testCreateAndReturnResponseCreated() throws Exception {
    // Input
    ShowDTO showDTO = new ShowDTO(10L, 1, "2022-05-25", "12:30", 150, 120);
    String jsonString = getJsonString(showDTO);
    // mock service
    when(showService.createShow(any(ShowDTO.class))).thenReturn(1L);

    mockMvc
            .perform(
                    MockMvcRequestBuilders.post(BASE_URL)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(jsonString)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(mvcResult -> mvcResult.getResponse().getHeader(HttpHeaders.LOCATION))
            .andReturn();
  }

  @Test
  public void testRemoveShow() throws Exception {
    doNothing().when(showService).removeShow(1);
    mockMvc
            .perform(
                    MockMvcRequestBuilders.delete(BASE_URL + "/1")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }

  private String getJsonString(Object input) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(input);
    return requestJson;
  }
}
