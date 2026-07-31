package com.ticketing.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.ticketing.enums.TransportType;


import jakarta.persistence.*;



@Entity
public class Transportation {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String transportNumber;



    @Enumerated(EnumType.STRING)
    private TransportType type;



    private String operatorName;



    private String source;



    private String destination;



    private LocalDateTime departureTime;



    private LocalDateTime arrivalTime;



    private String duration;



    private Integer totalSeats;



    private Integer availableSeats;



    private BigDecimal price;




    @OneToMany(
            mappedBy = "transportation",
            cascade = CascadeType.ALL
    )
    private List<TransportBooking> bookings =
            new ArrayList<>();





    public Transportation() {

    }





    public Long getId() {
        return id;
    }


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


    public Integer getAvailableSeats() {
        return availableSeats;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public List<TransportBooking> getBookings() {
        return bookings;
    }






    public void setId(Long id) {
        this.id = id;
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


    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setBookings(
            List<TransportBooking> bookings
    ) {
        this.bookings = bookings;
    }

}