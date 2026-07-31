package com.ticketing.dto.admin;


import java.math.BigDecimal;

import com.ticketing.enums.AccommodationType;



public class AdminAccommodationRequest {



    private String name;


    private AccommodationType type;


    private String city;


    private String address;


    private String description;


    private Double rating;


    private BigDecimal pricePerNight;


    private Integer availableRooms;






    public String getName() {
        return name;
    }


    public AccommodationType getType() {
        return type;
    }


    public String getCity() {
        return city;
    }


    public String getAddress() {
        return address;
    }


    public String getDescription() {
        return description;
    }


    public Double getRating() {
        return rating;
    }


    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }


    public Integer getAvailableRooms() {
        return availableRooms;
    }








    public void setName(String name) {
        this.name = name;
    }


    public void setType(AccommodationType type) {
        this.type = type;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setRating(Double rating) {
        this.rating = rating;
    }


    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }


}