package com.ticketing.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ticketing.enums.BookingStatus;

public class AccommodationBookingResponse {

    private Long id;

    private Long accommodationId;

    private String accommodationName;

    private String city;

    private Integer roomsBooked;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private BigDecimal totalAmount;

    private BookingStatus status;

    private LocalDateTime bookingDate;

    public AccommodationBookingResponse() {
    }

    public AccommodationBookingResponse(
            Long id,
            Long accommodationId,
            String accommodationName,
            String city,
            Integer roomsBooked,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            BigDecimal totalAmount,
            BookingStatus status,
            LocalDateTime bookingDate
    ) {

        this.id = id;
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.city = city;
        this.roomsBooked = roomsBooked;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public String getCity() {
        return city;
    }

    public Integer getRoomsBooked() {
        return roomsBooked;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
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