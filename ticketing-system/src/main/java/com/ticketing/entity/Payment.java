package com.ticketing.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ticketing.enums.PaymentMethod;
import com.ticketing.enums.PaymentStatus;

import jakarta.persistence.*;



@Entity
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private Long bookingId;



    private String bookingType;



    private BigDecimal amount;



    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;



    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;



    private String transactionId;



    private LocalDateTime paymentDate;





    public Payment() {

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


    public PaymentMethod getPaymentMethod() {
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


    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

}