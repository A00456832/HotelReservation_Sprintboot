package com.assignment.HotelRestAPI.Controller;

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
import java.time.Period;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelRepository hotelRepository;

    // Below function is used to fetch the reservation by its id
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

    // Below function is used to make a reservation by Hotel Id.
    @PostMapping(path = "/{hotelId}")
    public ResponseEntity<ReservationRes> saveReservation(@PathVariable Long hotelId, @RequestBody Reservation reservation) {
        ReservationRes reservationRes = new ReservationRes();
        try{
            // As a part of requirement, custom error handling is done.
            // Return error when either checkin date or checkout date is null along with suitable HTTP status code
            if (reservation.getCheckinDate() == null || reservation.getCheckoutDate() == null)
            {
                reservationRes.setMessage("Error: Either checkin date or checkout date is null.");
                return new ResponseEntity<>(reservationRes,HttpStatus.NOT_ACCEPTABLE);
            }

            // Return error when checkout date is prior to checkin date along with suitable HTTP status code
            if (reservation.getCheckinDate().compareTo(reservation.getCheckoutDate())>= 0)
            {
                    reservationRes.setMessage("Error: Checkout date can not be prior to checkin date.");
                    return new ResponseEntity<>(reservationRes,HttpStatus.NOT_ACCEPTABLE);
            }

            // A reservation cannot be made without guest so return a error when guest count is zero.
            if (reservation.getGuestList().size() == 0)
            {
                    reservationRes.setMessage("Error: At least 1 guest must be provided.");
                    return new ResponseEntity<>(reservationRes,HttpStatus.NOT_ACCEPTABLE);
            }

            // As an audit, the timestamp is captured whenever reservation is made
            reservation.setReservationDateTime(LocalDateTime.now());

            // Reservation is made against hotel. Technically, reservation.hotelId is FK to the hotel.Id
            // So we have to fetch the corresponding hotel and pass to the reservation.setHotel method.
            Hotel hotel = hotelRepository.findById(hotelId).get();
            hotel.setAvailable(false);
            reservation.setHotel(hotel);

            // To implment complex requirement where Total Price of the stay needs to be calculated using...
            // TotalPrice = Hotel.Price  * (Checkout date - Checkin date)
            // Below code performs the same calculation using standard Period class.
            Period actualStayInDays = Period.between(reservation.getCheckinDate(), reservation.getCheckoutDate());
            reservation.setTotalPrice(hotel.getPrice() * actualStayInDays.getDays());
            // Save the instance of reservation
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

    // Below function is used to delete any reservation made so reservation id needs to be supplied here.
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservationRes> deleteReservationById(@PathVariable Long id) {
        ReservationRes reservationRes = new ReservationRes();
        try{
            reservationRepository.deleteById(id);
            reservationRes.setMessage("Successfully deleted : " + id);
            return new ResponseEntity<>(reservationRes, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e) {
            // If valid reservation id is not present in the db then informative error message is provided with valid status code.
            reservationRes.setMessage("Please provide valid reservation Id.");
            return new ResponseEntity<>(reservationRes, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            reservationRes.setMessage(e.getMessage());
            return new ResponseEntity<>(reservationRes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
