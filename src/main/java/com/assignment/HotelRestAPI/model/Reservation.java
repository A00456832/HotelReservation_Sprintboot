package com.assignment.HotelRestAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

//    private Long hotelId;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date checkinDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date checkoutDate;

    private float totalPrice;

    private LocalDateTime reservationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Hotel getHotel() {
        return hotel;
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

//    public Long getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(Long hotelId) {
//        this.hotelId = hotelId;
//    }
}
