package com.ticketing.controller;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.request.AccommodationBookingRequest;
import com.ticketing.dto.response.AccommodationBookingResponse;
import com.ticketing.service.AccommodationBookingService;



@RestController
@RequestMapping("/accommodation-bookings")
public class AccommodationBookingController {



    private final AccommodationBookingService accommodationBookingService;



    public AccommodationBookingController(
            AccommodationBookingService accommodationBookingService
    ) {

        this.accommodationBookingService =
                accommodationBookingService;

    }









    @PostMapping
    public ResponseEntity<AccommodationBookingResponse> book(
            @RequestBody AccommodationBookingRequest request
    ) {


        return ResponseEntity.ok(

                accommodationBookingService.bookAccommodation(
                        request
                )

        );

    }









    @GetMapping("/my")
    public ResponseEntity<List<AccommodationBookingResponse>> myBookings() {


        return ResponseEntity.ok(

                accommodationBookingService.getMyBookings()

        );

    }









    @GetMapping("/{id}")
    public ResponseEntity<AccommodationBookingResponse> getById(
            @PathVariable Long id
    ) {


        return ResponseEntity.ok(

                accommodationBookingService.getBookingById(id)

        );

    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Long id
    ) {


        accommodationBookingService
                .cancelAccommodationBooking(id);



        return ResponseEntity.ok(

                "Accommodation booking cancelled successfully"

        );

    }



}