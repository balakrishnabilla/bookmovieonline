package com.microservices.bookmovie.premiereservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedEntityGraph(
    name = "theater-shows-graph",
    attributeNodes = @NamedAttributeNode(value = "shows"))
public class Theatre {
  @Id
  private Long theatreId;

  private String theatreName;

  private int cityId;

  private int movieId;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "theatreId")
  private List<Show> shows;

  public Theatre() {}

  public Theatre(Long theatreId, String theatreName, int cityId, int movieId, List<Show> shows) {
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

  public void setTheatreId(Long theatreId) {
    this.theatreId = theatreId;
  }

  public String getTheatreName() {
    return theatreName;
  }

  public void setTheatreName(String theatreName) {
    this.theatreName = theatreName;
  }

  public int getCityId() {
    return cityId;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public void setShows(List<Show> shows) {
    this.shows = shows;
  }

  public List<Show> getShows() {
    return this.shows;
  }
}
