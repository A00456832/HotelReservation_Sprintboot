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

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelName() {
        HotelListRes hotelListRes = new HotelListRes();
        hotelListRes.setHotels(hotelRepository.findAll());
        hotelListRes.setMessage("Successfully retrived");
        return new ResponseEntity(hotelListRes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HotelRes> saveHotel(@RequestBody Hotel hotel) {
        HotelRes hotelRes = new HotelRes();
        try{
            hotel.setFinalCost(hotel.getPrice()*hotel.getnoDaysBooked());
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
