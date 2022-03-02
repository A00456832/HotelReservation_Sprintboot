package com.assignment.HotelRestAPI.dto;

import com.assignment.HotelRestAPI.model.Hotel;

public class HotelRes {
    String message;
    Hotel hotel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
