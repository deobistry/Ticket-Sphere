package com.ticketing.dto.request;


import java.math.BigDecimal;


import com.ticketing.enums.AccommodationType;



public class AccommodationSearchRequest {


    private String city;


    private AccommodationType type;


    private BigDecimal minPrice;


    private BigDecimal maxPrice;


    private Double minRating;


    private Integer minRooms;





    public AccommodationSearchRequest() {

    }





    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }





    public AccommodationType getType() {
        return type;
    }


    public void setType(AccommodationType type) {
        this.type = type;
    }





    public BigDecimal getMinPrice() {
        return minPrice;
    }


    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }





    public BigDecimal getMaxPrice() {
        return maxPrice;
    }


    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }





    public Double getMinRating() {
        return minRating;
    }


    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }





    public Integer getMinRooms() {
        return minRooms;
    }


    public void setMinRooms(Integer minRooms) {
        this.minRooms = minRooms;
    }

}