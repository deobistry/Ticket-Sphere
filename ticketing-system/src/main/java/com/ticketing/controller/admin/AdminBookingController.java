package com.ticketing.controller.admin;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.admin.AdminBookingResponse;
import com.ticketing.service.admin.AdminBookingService;



@RestController
@RequestMapping("/admin/bookings")
public class AdminBookingController {



    private final AdminBookingService adminBookingService;





    public AdminBookingController(
            AdminBookingService adminBookingService
    ){

        this.adminBookingService =
                adminBookingService;

    }








    @GetMapping
    public ResponseEntity<List<AdminBookingResponse>> getBookings(){


        return ResponseEntity.ok(

            adminBookingService.getAllBookings()

        );


    }



}