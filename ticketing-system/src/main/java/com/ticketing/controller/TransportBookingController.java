package com.ticketing.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import com.ticketing.dto.request.TransportBookingRequest;
import com.ticketing.dto.response.TransportBookingResponse;
import com.ticketing.service.TransportBookingService;



@RestController
@RequestMapping("/transport-bookings")
public class TransportBookingController {



    private final TransportBookingService transportBookingService;



    public TransportBookingController(
            TransportBookingService transportBookingService
    ) {

        this.transportBookingService =
                transportBookingService;

    }








    @PostMapping
    public ResponseEntity<TransportBookingResponse> book(
            @RequestBody TransportBookingRequest request
    ) {


        return ResponseEntity.ok(

                transportBookingService.bookTransportation(
                        request
                )

        );

    }









    @GetMapping("/my")
    public ResponseEntity<List<TransportBookingResponse>> myBookings() {


        return ResponseEntity.ok(

                transportBookingService.getMyBookings()

        );

    }









    @GetMapping("/{id}")
    public ResponseEntity<TransportBookingResponse> getById(
            @PathVariable Long id
    ) {


        return ResponseEntity.ok(

                transportBookingService.getBookingById(id)

        );

    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Long id
    ) {


        transportBookingService.cancelTransportBooking(
                id
        );


        return ResponseEntity.ok(

                "Transportation booking cancelled successfully"

        );

    }



}