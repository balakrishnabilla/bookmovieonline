package com.microservices.bookmovie.premiereservice.repo;

import com.microservices.bookmovie.premiereservice.entity.Theatre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

  @Query(
      " select t from Theatre t , Show s where s.theatreId = t.theatreId and t.cityId = :cityId "
          + "and t.movieId = :movieId and s.showDate >= :date")
  List<Theatre> getShows(int cityId, int movieId, LocalDate date);

  @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "theater-shows-graph")
  List<Theatre> findByCityIdAndMovieId(int cityId, int movieId);

  List<Theatre> findByMovieId(int movieId);
}
