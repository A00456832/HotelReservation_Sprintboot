package com.assignment.HotelRestAPI.dto;

import com.assignment.HotelRestAPI.model.Hotel;

import java.util.List;

public class HotelListRes {
    String message;
    List<Hotel> hotels;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
