package com.ticketing.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;



public class BookingHistoryResponse {



    private Long bookingId;


    private String bookingType;


    private String itemName;


    private BigDecimal amount;


    private String status;


    private LocalDateTime bookingDate;






    public BookingHistoryResponse(
            Long bookingId,
            String bookingType,
            String itemName,
            BigDecimal amount,
            String status,
            LocalDateTime bookingDate
    ) {

        this.bookingId = bookingId;
        this.bookingType = bookingType;
        this.itemName = itemName;
        this.amount = amount;
        this.status = status;
        this.bookingDate = bookingDate;

    }







    public Long getBookingId() {
        return bookingId;
    }


    public String getBookingType() {
        return bookingType;
    }


    public String getItemName() {
        return itemName;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public String getStatus() {
        return status;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

}