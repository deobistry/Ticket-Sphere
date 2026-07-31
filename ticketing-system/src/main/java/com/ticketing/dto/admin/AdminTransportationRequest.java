package com.ticketing.dto.admin;


import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.ticketing.enums.TransportType;



public class AdminTransportationRequest {



    private String transportNumber;


    private TransportType type;


    private String operatorName;


    private String source;


    private String destination;


    private LocalDateTime departureTime;


    private LocalDateTime arrivalTime;


    private String duration;


    private Integer totalSeats;


    private BigDecimal price;







    public String getTransportNumber() {
        return transportNumber;
    }


    public TransportType getType() {
        return type;
    }


    public String getOperatorName() {
        return operatorName;
    }


    public String getSource() {
        return source;
    }


    public String getDestination() {
        return destination;
    }


    public LocalDateTime getDepartureTime() {
        return departureTime;
    }


    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }


    public String getDuration() {
        return duration;
    }


    public Integer getTotalSeats() {
        return totalSeats;
    }


    public BigDecimal getPrice() {
        return price;
    }






    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }


    public void setType(TransportType type) {
        this.type = type;
    }


    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }


    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }


    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}