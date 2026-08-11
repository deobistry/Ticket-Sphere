package com.ticketing.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ticketing.enums.BookingStatus;

public class TransportBookingResponse {

    private Long id;

    private Long transportationId;

    private String transportNumber;

    private String source;

    private String destination;

    private LocalDateTime departureTime;

    private Integer seatsBooked;

    private BigDecimal totalAmount;

    private BookingStatus status;

    private LocalDateTime bookingDate;

    public TransportBookingResponse() {
    }

    public TransportBookingResponse(
            Long id,
            Long transportationId,
            String transportNumber,
            String source,
            String destination,
            LocalDateTime departureTime,
            Integer seatsBooked,
            BigDecimal totalAmount,
            BookingStatus status,
            LocalDateTime bookingDate
    ) {

        this.id = id;
        this.transportationId = transportationId;
        this.transportNumber = transportNumber;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatsBooked = seatsBooked;
        this.totalAmount = totalAmount;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public Long getTransportationId() {
        return transportationId;
    }

    public String getTransportNumber() {
        return transportNumber;
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
}