package com.assignment.HotelRestAPI.Controller;

import com.assignment.HotelRestAPI.dto.HotelRes;
import com.assignment.HotelRestAPI.dto.ReservationRes;
import com.assignment.HotelRestAPI.model.Hotel;
import com.assignment.HotelRestAPI.model.Reservation;
import com.assignment.HotelRestAPI.repository.HotelRepository;
import com.assignment.HotelRestAPI.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping(path="/{id}")
    public ResponseEntity<ReservationRes> getReservationById(@PathVariable Long id) {
        ReservationRes reservationRes = new ReservationRes();
        try {
            Optional<Reservation> reservation = reservationRepository.findById(id);
            reservationRes.setReservation(reservation.get());
            reservationRes.setMessage("Successfully retrived : " + id);
            return new ResponseEntity<>(reservationRes, HttpStatus.OK);
        }catch (Exception e) {
            reservationRes.setMessage(e.getMessage());
            return new ResponseEntity<>(reservationRes, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/{hotelId}")
    public ResponseEntity<ReservationRes> saveReservation(@PathVariable Long hotelId, @RequestBody Reservation reservation) {
        ReservationRes reservationRes = new ReservationRes();
        try{
            reservation.setReservationDateTime(LocalDateTime.now());
            Hotel hotel = hotelRepository.findById(hotelId).get();
            reservation.setHotel(hotel);
            Reservation newRes = reservationRepository.save(reservation);
            reservationRes.setReservation(newRes);
            reservationRes.setMessage("Successfully Created by id : " + newRes.getId());
            return new ResponseEntity<>(reservationRes,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            reservationRes.setMessage(e.getMessage());
            return new ResponseEntity<>(reservationRes,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservationRes> deleteReservationById(@PathVariable Long id) {
        ReservationRes reservationRes = new ReservationRes();
        try{
            reservationRepository.deleteById(id);
            reservationRes.setMessage("Successfully deleted : " + id);
            return new ResponseEntity<>(reservationRes, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            reservationRes.setMessage("Please provide valid reservation Id.");
            return new ResponseEntity<>(reservationRes, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            reservationRes.setMessage(e.getMessage());
            return new ResponseEntity<>(reservationRes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
