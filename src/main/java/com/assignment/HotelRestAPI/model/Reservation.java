package com.assignment.HotelRestAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @GeneratedValue     // Auto incremented identity column
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false) // One hotel can have many reservations
    private Hotel hotel;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    List<Guest> guestList = new ArrayList<>();
    // Reservation can have guest list. guest.reservationId is FK to reservation.id

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkinDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkoutDate;

    private float totalPrice;
    // To store the total cost of the stay. Total Price = ((Checkout date) - (Checkin date) ) * (Price per day)

    private LocalDateTime reservationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }
}
