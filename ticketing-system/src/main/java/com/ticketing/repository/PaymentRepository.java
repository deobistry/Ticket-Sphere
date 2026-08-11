package com.ticketing.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.entity.Payment;



public interface PaymentRepository 
        extends JpaRepository<Payment, Long> {


    List<Payment> findByBookingId(Long bookingId);

}