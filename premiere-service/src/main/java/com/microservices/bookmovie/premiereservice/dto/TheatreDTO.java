package com.microservices.bookmovie.premiereservice.dto;

import java.io.Serializable;
import java.util.List;

public class TheatreDTO implements Serializable {
  private Long theatreId;
  private String theatreName;
  private int cityId;
  private int movieId;

  private List<ShowDTO> shows;
  public TheatreDTO(){}
  public TheatreDTO(
      Long theatreId, String theatreName, int cityId, int movieId, List<ShowDTO> shows) {
    super();
    this.theatreId = theatreId;
    this.theatreName = theatreName;
    this.cityId = cityId;
    this.movieId = movieId;
    this.shows = shows;
  }

  public Long getTheatreId() {
    return theatreId;
  }

  public String getTheatreName() {
    return theatreName;
  }

  public int getCityId() {
    return cityId;
  }

  public int getMovieId() {
    return movieId;
  }

  public List<ShowDTO> getShows() {
    return shows;
  }

  public void setTheatreId(Long theatreId) {
    this.theatreId = theatreId;
  }

  public void setTheatreName(String theatreName) {
    this.theatreName = theatreName;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public void setShows(List<ShowDTO> shows) {
    this.shows = shows;
  }
}
