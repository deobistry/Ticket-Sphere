package com.ticketing.service.admin;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;


import com.ticketing.dto.admin.AdminBookingResponse;
import com.ticketing.entity.AccommodationBooking;
import com.ticketing.entity.TransportBooking;
import com.ticketing.repository.AccommodationBookingRepository;
import com.ticketing.repository.TransportBookingRepository;



@Service
public class AdminBookingService {



    private final TransportBookingRepository transportBookingRepository;


    private final AccommodationBookingRepository accommodationBookingRepository;





    public AdminBookingService(
            TransportBookingRepository transportBookingRepository,
            AccommodationBookingRepository accommodationBookingRepository
    ){

        this.transportBookingRepository =
                transportBookingRepository;


        this.accommodationBookingRepository =
                accommodationBookingRepository;

    }








    public List<AdminBookingResponse> getAllBookings(){


        List<AdminBookingResponse> bookings =
                new ArrayList<>();





        List<TransportBooking> transportBookings =
                transportBookingRepository.findAll();





        for(TransportBooking booking : transportBookings){


            bookings.add(

                new AdminBookingResponse(

                    booking.getId(),

                    "TRANSPORT",

                    booking.getUser().getName(),

                    booking.getUser().getEmail(),

                    booking.getTransportation()
                           .getTransportNumber(),

                    booking.getTotalAmount(),

                    booking.getStatus().name(),

                    booking.getBookingDate()

                )

            );


        }







        List<AccommodationBooking> accommodationBookings =
                accommodationBookingRepository.findAll();






        for(AccommodationBooking booking : accommodationBookings){


            bookings.add(

                new AdminBookingResponse(

                    booking.getId(),

                    "ACCOMMODATION",

                    booking.getUser().getName(),

                    booking.getUser().getEmail(),

                    booking.getAccommodation()
                           .getName(),

                    booking.getTotalAmount(),

                    booking.getStatus().name(),

                    booking.getBookingDate()

                )

            );


        }





        return bookings;


    }


}