package com.ticketing.dto.request;

import java.math.BigDecimal;

import com.ticketing.enums.PaymentMethod;

public class PaymentRequest {

    private Long bookingId;

    private String bookingType;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    public Long getBookingId() {
        return bookingId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}