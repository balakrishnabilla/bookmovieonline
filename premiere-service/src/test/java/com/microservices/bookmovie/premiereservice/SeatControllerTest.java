package com.microservices.bookmovie.premiereservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.microservices.bookmovie.premiereservice.constants.SeatStatus;
import com.microservices.bookmovie.premiereservice.controller.SeatController;
import com.microservices.bookmovie.premiereservice.dto.SeatDTO;
import com.microservices.bookmovie.premiereservice.dto.SeatInfoDTO;
import com.microservices.bookmovie.premiereservice.service.SeatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SeatController.class)
public class SeatControllerTest {
  public static final String BASE_URL = "/premiere-service";
  @Autowired private MockMvc mockMvc;
  @MockBean private SeatService seatService;
  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void contextLoads() {}

  @Test
  public void testGetSeatsByTheater() throws Exception {
    // input
    List<SeatDTO> seatDTOs = new ArrayList<>();
    seatDTOs.add(new SeatDTO(1L, 1, "a12", SeatStatus.AVAILABLE.getValue(), 190));

    when(seatService.getSeats(anyInt())).thenReturn(seatDTOs);

    MvcResult result =
        mockMvc.perform(get(BASE_URL + "/seats?showId=1")).andExpect(status().isOk()).andReturn();

    List<SeatDTO> jsonResponse =
        mapper.readValue(
            result.getResponse().getContentAsString(), new TypeReference<List<SeatDTO>>() {});
    assertEquals(1, jsonResponse.size());
    assertEquals(new Long(1), jsonResponse.get(0).getSeatId());
    assertEquals(190.0, jsonResponse.get(0).getPrice());
    assertEquals("a12", jsonResponse.get(0).getSeatNum());
    assertEquals(SeatStatus.AVAILABLE.getValue(), jsonResponse.get(0).getStatus());
  }

  @Test
  public void testLockSeats() throws Exception {
    // input
    SeatInfoDTO seatInfoDTO = new SeatInfoDTO(10, Arrays.asList("a1"), 120);
    String jsonString = getJsonString(seatInfoDTO);
    // mock service
    doNothing().when(seatService).lockSeats(any());

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(BASE_URL + "/lockseats")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON));
  }

  private String getJsonString(Object input) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(input);
    return requestJson;
  }
}
