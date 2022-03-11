package com.assignment.HotelRestAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @GeneratedValue // Auto incremented
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private int price;

    @Column(nullable = false, length = 100)
    private String city;

    @OneToMany(cascade=CascadeType.ALL) // FK to Reservation.Hotel_Id and has 1:Many relationship
    @JoinColumn(name = "hotel_id")
    List<Reservation> reservationList = new ArrayList<>();

    private int starRating;

    private boolean isAvailable; // This flag is used to maintain the availability of the hotel.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
