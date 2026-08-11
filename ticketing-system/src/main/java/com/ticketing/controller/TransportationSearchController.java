package com.ticketing.controller;


import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ticketing.dto.request.TransportationSearchRequest;
import com.ticketing.dto.response.TransportationResponse;
import com.ticketing.service.TransportationService;



@RestController
@RequestMapping("/transportations")
public class TransportationSearchController {


    private final TransportationService transportationService;



    public TransportationSearchController(
            TransportationService transportationService
    ) {

        this.transportationService = transportationService;
    }





    @GetMapping("/search")
    public List<TransportationResponse> search(
            @ModelAttribute TransportationSearchRequest request
    ) {


        return transportationService.searchTransportation(
                request
        );

    }

}