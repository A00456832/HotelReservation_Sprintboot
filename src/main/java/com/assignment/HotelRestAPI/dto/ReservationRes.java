package com.assignment.HotelRestAPI.dto;

import com.assignment.HotelRestAPI.model.Reservation;

public class ReservationRes {
    String message;
    Reservation reservation;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
