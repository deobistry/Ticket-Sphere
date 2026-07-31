package com.ticketing.service;


import java.math.BigDecimal;


import org.springframework.stereotype.Service;


import com.ticketing.dto.response.AdminDashboardResponse;
import com.ticketing.repository.AccommodationBookingRepository;
import com.ticketing.repository.TransportBookingRepository;
import com.ticketing.repository.UserRepository;



@Service
public class AdminService {



    private final UserRepository userRepository;

    private final TransportBookingRepository transportBookingRepository;

    private final AccommodationBookingRepository accommodationBookingRepository;



    public AdminService(
            UserRepository userRepository,
            TransportBookingRepository transportBookingRepository,
            AccommodationBookingRepository accommodationBookingRepository
    ){

        this.userRepository = userRepository;
        this.transportBookingRepository = transportBookingRepository;
        this.accommodationBookingRepository = accommodationBookingRepository;
    }





    public AdminDashboardResponse dashboard(){



        Long users =
                userRepository.count();



        Long transportBookings =
                transportBookingRepository.count();



        Long accommodationBookings =
                accommodationBookingRepository.count();



        return new AdminDashboardResponse(

                users,

                transportBookings,

                accommodationBookings,

                BigDecimal.ZERO,

                transportBookings + accommodationBookings,

                0L

        );

    }


}