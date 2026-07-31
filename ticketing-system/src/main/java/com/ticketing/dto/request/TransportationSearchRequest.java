package com.ticketing.dto.request;


import java.math.BigDecimal;
import java.time.LocalDate;


public class TransportationSearchRequest {


    private String source;


    private String destination;


    private String type;


    private LocalDate travelDate;


    private BigDecimal minPrice;


    private BigDecimal maxPrice;


    private Integer minSeats;




    public TransportationSearchRequest() {
    }




    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }




    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }




    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }




    public LocalDate getTravelDate() {
        return travelDate;
    }


    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
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




    public Integer getMinSeats() {
        return minSeats;
    }


    public void setMinSeats(Integer minSeats) {
        this.minSeats = minSeats;
    }
}