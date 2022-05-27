package com.microservices.bookmovie.bookingservice.repo;

import com.microservices.bookmovie.bookingservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingServiceRepository extends JpaRepository<Ticket, Integer> {

}
