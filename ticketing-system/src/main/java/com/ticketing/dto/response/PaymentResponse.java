package com.ticketing.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ticketing.enums.PaymentStatus;



public class PaymentResponse {


    private Long id;


    private Long bookingId;


    private String bookingType;


    private BigDecimal amount;


    private String paymentMethod;


    private PaymentStatus paymentStatus;


    private String transactionId;


    private LocalDateTime paymentDate;





    public PaymentResponse(
            Long id,
            Long bookingId,
            String bookingType,
            BigDecimal amount,
            String paymentMethod,
            PaymentStatus paymentStatus,
            String transactionId,
            LocalDateTime paymentDate
    ) {

        this.id = id;
        this.bookingId = bookingId;
        this.bookingType = bookingType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
    }






    public Long getId() {
        return id;
    }


    public Long getBookingId() {
        return bookingId;
    }


    public String getBookingType() {
        return bookingType;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }


    public String getTransactionId() {
        return transactionId;
    }


    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

}