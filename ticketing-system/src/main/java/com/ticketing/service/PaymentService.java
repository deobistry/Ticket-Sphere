package com.ticketing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.dto.request.PaymentRequest;
import com.ticketing.dto.response.PaymentResponse;
import com.ticketing.entity.Payment;
import com.ticketing.enums.PaymentStatus;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(
            PaymentRepository paymentRepository
    ) {

        this.paymentRepository = paymentRepository;

    }

    @Transactional
    public PaymentResponse makePayment(
            PaymentRequest request
    ) {

        if (request == null) {

            throw new RuntimeException(
                    "Payment request is required"
            );

        }

        if (request.getBookingId() == null) {

            throw new RuntimeException(
                    "Booking id is required"
            );

        }

        if (request.getBookingType() == null ||
                request.getBookingType().isBlank()) {

            throw new RuntimeException(
                    "Booking type is required"
            );

        }

        if (request.getAmount() == null ||
                request.getAmount().signum() <= 0) {

            throw new RuntimeException(
                    "Amount must be greater than zero"
            );

        }

        if (request.getPaymentMethod() == null) {

            throw new RuntimeException(
                    "Payment method is required"
            );

        }

        Payment payment =
                new Payment();

        payment.setBookingId(
                request.getBookingId()
        );

        payment.setBookingType(
                request.getBookingType()
        );

        payment.setAmount(
                request.getAmount()
        );

        payment.setPaymentMethod(
                request.getPaymentMethod()
        );

        payment.setPaymentStatus(
                PaymentStatus.SUCCESS
        );

        payment.setTransactionId(
                UUID.randomUUID().toString()
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        Payment savedPayment =
                paymentRepository.save(
                        payment
                );

        return convert(
                savedPayment
        );

    }

    public List<PaymentResponse> getPayments(
            Long bookingId
    ) {

        if (bookingId == null) {

            throw new RuntimeException(
                    "Booking id is required"
            );

        }

        return paymentRepository
                .findByBookingId(
                        bookingId
                )
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }

    public PaymentResponse getPaymentById(
            Long id
    ) {

        Payment payment =
                paymentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Payment not found"
                        )
                );

        return convert(
                payment
        );

    }

    @Transactional
    public void cancelPayment(
            Long id
    ) {

        Payment payment =
                paymentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Payment not found"
                        )
                );

        payment.setPaymentStatus(
                PaymentStatus.FAILED
        );

        paymentRepository.save(
                payment
        );

    }

    private PaymentResponse convert(
            Payment payment
    ) {

        return new PaymentResponse(

                payment.getId(),

                payment.getBookingId(),

                payment.getBookingType(),

                payment.getAmount(),

                payment.getPaymentMethod().name(),

                payment.getPaymentStatus(),

                payment.getTransactionId(),

                payment.getPaymentDate()

        );

    }

}