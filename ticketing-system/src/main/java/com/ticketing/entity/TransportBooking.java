package com.ticketing.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ticketing.enums.BookingStatus;

import jakarta.persistence.*;


@Entity
public class TransportBooking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToOne
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;



    private Integer seatsBooked;



    private BigDecimal totalAmount;



    @Enumerated(EnumType.STRING)
    private BookingStatus status;



    private LocalDateTime bookingDate;



    public TransportBooking() {
    }



    public Long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }


    public Transportation getTransportation() {
        return transportation;
    }


    public Integer getSeatsBooked() {
        return seatsBooked;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public BookingStatus getStatus() {
        return status;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }



    public void setUser(User user) {
        this.user = user;
    }


    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }


    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public void setStatus(BookingStatus status) {
        this.status = status;
    }


    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}