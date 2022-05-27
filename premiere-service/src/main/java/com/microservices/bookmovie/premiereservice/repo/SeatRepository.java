package com.microservices.bookmovie.premiereservice.repo;

import com.microservices.bookmovie.premiereservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
  public List<Seat> findByShowIdAndSeatNumIn(int showId, List<String> seatNumbers);
  public List<Seat> findByShowIdAndStatus(int showId, int status);
}
