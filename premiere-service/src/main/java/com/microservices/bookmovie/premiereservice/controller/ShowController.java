package com.microservices.bookmovie.premiereservice.controller;

import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.dto.TheatreDTO;
import com.microservices.bookmovie.premiereservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/premiere-service")
public class ShowController {

  @Autowired ShowService showService;

  @GetMapping("/shows")
  public @ResponseBody List<TheatreDTO> getTheatres(
      @NumberFormat(style = NumberFormat.Style.NUMBER) @RequestParam("cityId") int cityId,
      @NumberFormat(style = NumberFormat.Style.NUMBER) @RequestParam("movieId") int movieId,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("date") LocalDate localDate)
      throws ParseException {
    return showService.getShows(cityId, movieId, localDate);
  }

  @GetMapping("/theater")
  public @ResponseBody List<ShowDTO> getShows(
      @NumberFormat(style = NumberFormat.Style.NUMBER) @RequestParam("theaterId") int theaterId)
      throws ParseException {
    return showService.getShows(theaterId);
  }

  @PostMapping("/shows")
  public ResponseEntity<Object> createShow(@Valid ShowDTO showDTO) throws ParseException {
    Long showId = showService.createShow(showDTO);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(showId)
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/shows/{id}")
  public ResponseEntity<Object> removeFilterRule(@PathVariable long id) {
    showService.removeShow(id);
    return ResponseEntity.noContent().build();
  }
}
