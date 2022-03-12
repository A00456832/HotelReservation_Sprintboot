package com.assignment.HotelRestAPI.repository;

import com.assignment.HotelRestAPI.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
