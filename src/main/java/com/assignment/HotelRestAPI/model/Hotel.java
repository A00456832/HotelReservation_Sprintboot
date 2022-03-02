package com.assignment.HotelRestAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private int price;

    @Column(nullable = false, length = 100)
    private String city;

    private int starRating;

    @Column(nullable = true, length = 300)
    private String amenities;

        @Column(nullable = true)
        private int noDaysBooked;

        @Column(nullable = true)
        private int FinalCost;


         public int getnoDaysBooked() {
            return noDaysBooked;
        }

        public void setnoDaysBooked(int noDaysBooked) {
            this.noDaysBooked = noDaysBooked;
        }

        public int getFinalCost() {
            return FinalCost;
        }

        public void setFinalCost(int FinalCost) {
            this.FinalCost = FinalCost;
        }


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

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
}
