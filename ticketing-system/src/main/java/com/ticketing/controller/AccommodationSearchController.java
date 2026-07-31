package com.ticketing.controller;


import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ticketing.dto.request.AccommodationSearchRequest;
import com.ticketing.dto.response.AccommodationResponse;
import com.ticketing.service.AccommodationService;



@RestController
@RequestMapping("/accommodations")
public class AccommodationSearchController {


    private final AccommodationService accommodationService;



    public AccommodationSearchController(
            AccommodationService accommodationService
    ) {

        this.accommodationService = accommodationService;
    }





    @GetMapping("/search")
    public List<AccommodationResponse> search(
            @ModelAttribute AccommodationSearchRequest request
    ) {


        return accommodationService.searchAccommodation(
                request
        );

    }

}