package com.microservices.bookmovie.premiereservice.repo;

import com.microservices.bookmovie.premiereservice.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

  @Query(
      " select showId, theatreId, showTime, showDate, totalSeats, availableSeats from Show s where s.theatreId = :theatreId")
  public List<Show> getShowList(@Param("theatreId") int theatreId);

  public List<Show> findByTheatreId(int theatreId);
}
