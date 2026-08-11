package com.ticketing.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.response.AdminDashboardResponse;
import com.ticketing.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {



    private final AdminService adminService;



    public AdminController(
            AdminService adminService
    ){

        this.adminService = adminService;
    }





    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardResponse> dashboard(){


        return ResponseEntity.ok(
                adminService.dashboard()
        );

    }

}