package com.ticketing.controller;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.ticketing.dto.response.BookingHistoryResponse;
import com.ticketing.service.BookingHistoryService;




@RestController
@RequestMapping("/users")
public class BookingHistoryController {



    private final BookingHistoryService bookingHistoryService;







    public BookingHistoryController(
            BookingHistoryService bookingHistoryService
    ) {

        this.bookingHistoryService =
                bookingHistoryService;

    }








    @GetMapping("/bookings")
    public ResponseEntity<List<BookingHistoryResponse>> history() {



        return ResponseEntity.ok(
                bookingHistoryService.getHistory()
        );

    }


}