package com.ticketing.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;


import com.ticketing.dto.response.BookingHistoryResponse;
import com.ticketing.entity.AccommodationBooking;
import com.ticketing.entity.TransportBooking;
import com.ticketing.repository.AccommodationBookingRepository;
import com.ticketing.repository.TransportBookingRepository;
import com.ticketing.security.UserContext;



@Service
public class BookingHistoryService {



    private final TransportBookingRepository transportBookingRepository;


    private final AccommodationBookingRepository accommodationBookingRepository;


    private final UserContext userContext;







    public BookingHistoryService(
            TransportBookingRepository transportBookingRepository,
            AccommodationBookingRepository accommodationBookingRepository,
            UserContext userContext
    ) {

        this.transportBookingRepository =
                transportBookingRepository;

        this.accommodationBookingRepository =
                accommodationBookingRepository;

        this.userContext =
                userContext;

    }








    public List<BookingHistoryResponse> getHistory() {



        if(userContext.getUserId()==null) {

            throw new RuntimeException(
                    "User not authenticated"
            );

        }






        List<BookingHistoryResponse> history =
                new ArrayList<>();








        List<TransportBooking> transportBookings =
                transportBookingRepository
                .findByUserId(
                        userContext.getUserId()
                );






        for(TransportBooking booking : transportBookings) {


            history.add(

                    new BookingHistoryResponse(

                            booking.getId(),

                            "TRANSPORT",

                            booking.getTransportation()
                            .getTransportNumber(),

                            booking.getTotalAmount(),

                            booking.getStatus()
                            .name(),

                            booking.getBookingDate()

                    )

            );

        }









        List<AccommodationBooking> accommodationBookings =
                accommodationBookingRepository
                .findByUserId(
                        userContext.getUserId()
                );






        for(AccommodationBooking booking :
                accommodationBookings) {


            history.add(

                    new BookingHistoryResponse(

                            booking.getId(),

                            "ACCOMMODATION",

                            booking.getAccommodation()
                            .getName(),

                            booking.getTotalAmount(),

                            booking.getStatus()
                            .name(),

                            booking.getBookingDate()

                    )

            );

        }







        return history;

    }


}