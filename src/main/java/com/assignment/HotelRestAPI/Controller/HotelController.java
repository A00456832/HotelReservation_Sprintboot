package com.assignment.HotelRestAPI.Controller;

import com.assignment.HotelRestAPI.dto.HotelListRes;
import com.assignment.HotelRestAPI.dto.HotelRes;
import com.assignment.HotelRestAPI.model.Hotel;
import com.assignment.HotelRestAPI.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    // Below function is used to fetch the Hotel by id
    @GetMapping(path="/{id}")
    public ResponseEntity<HotelRes> getHotelName(@PathVariable Long id) {
        HotelRes hotelRes = new HotelRes();
        try {
            Optional<Hotel> hotel = hotelRepository.findById(id);
            hotelRes.setHotel(hotel.get());
            hotelRes.setMessage("Successfully retrived : " + id);
            return new ResponseEntity<>(hotelRes, HttpStatus.OK);
        }catch (Exception e) {
            hotelRes.setMessage(e.getMessage());
            return new ResponseEntity<>(hotelRes, HttpStatus.NOT_FOUND);
        }
    }

    // Below function is used to fetch all the hotels.
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelName(@RequestParam(required = false) Boolean isAvailable) {
        HotelListRes hotelListRes = new HotelListRes();
        if (isAvailable != null) {
            hotelListRes.setHotels(hotelRepository.getAvailableHotelList(isAvailable));
        } else {
            hotelListRes.setHotels(hotelRepository.findAll());
        }
        hotelListRes.setMessage("Successfully retrived");
        return new ResponseEntity(hotelListRes.getHotels(), HttpStatus.OK);
        
    }

    // Below function is written to save the hotel instance. By default, its IsAvailable flag is set to true.
    // Hotel Id is auto incremented and it is returned as a response.
    @PostMapping
    public ResponseEntity<HotelRes> saveHotel(@RequestBody Hotel hotel) {
        HotelRes hotelRes = new HotelRes();
        try{
            hotel.setAvailable(true);
            Hotel newHotel = hotelRepository.save(hotel);
            hotelRes.setHotel(newHotel);
            hotelRes.setMessage("Successfully Created by id : " + newHotel.getId());
            return new ResponseEntity<>(hotelRes,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            hotelRes.setMessage(e.getMessage());
            return new ResponseEntity<>(hotelRes,HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
