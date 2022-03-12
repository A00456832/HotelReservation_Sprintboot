package com.assignment.HotelRestAPI.repository;

import com.assignment.HotelRestAPI.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}

