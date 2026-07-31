package com.ticketing.dto.request;

import java.math.BigDecimal;

import com.ticketing.enums.AccommodationType;

public class AccommodationRequest {

    private String name;

    private AccommodationType type;

    private String city;

    private String address;

    private String description;

    private Double rating;

    private BigDecimal pricePerNight;

    private Integer availableRooms;


    public AccommodationRequest() {
    }


    public AccommodationRequest(
            String name,
            AccommodationType type,
            String city,
            String address,
            String description,
            Double rating,
            BigDecimal pricePerNight,
            Integer availableRooms
    ) {
        this.name = name;
        this.type = type;
        this.city = city;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public AccommodationType getType() {
        return type;
    }


    public void setType(AccommodationType type) {
        this.type = type;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Double getRating() {
        return rating;
    }


    public void setRating(Double rating) {
        this.rating = rating;
    }


    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }


    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


    public Integer getAvailableRooms() {
        return availableRooms;
    }


    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }
}