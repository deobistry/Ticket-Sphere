package com.ticketing.controller;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.request.PaymentRequest;
import com.ticketing.dto.response.PaymentResponse;
import com.ticketing.service.PaymentService;



@RestController
@RequestMapping("/payments")
public class PaymentController {



    private final PaymentService paymentService;




    public PaymentController(
            PaymentService paymentService
    ) {

        this.paymentService = paymentService;
    }







    @PostMapping
    public ResponseEntity<PaymentResponse> pay(
            @RequestBody PaymentRequest request
    ) {


        return ResponseEntity.ok(
                paymentService.makePayment(request)
        );

    }







    @GetMapping("/{bookingId}")
    public ResponseEntity<List<PaymentResponse>> history(
            @PathVariable Long bookingId
    ) {


        return ResponseEntity.ok(
                paymentService.getPayments(
                        bookingId
                )
        );

    }

}