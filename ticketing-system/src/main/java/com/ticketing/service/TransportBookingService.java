package com.ticketing.service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ticketing.dto.request.TransportBookingRequest;
import com.ticketing.dto.response.TransportBookingResponse;
import com.ticketing.entity.TransportBooking;
import com.ticketing.entity.Transportation;
import com.ticketing.entity.User;
import com.ticketing.enums.BookingStatus;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.TransportBookingRepository;
import com.ticketing.repository.TransportationRepository;
import com.ticketing.repository.UserRepository;
import com.ticketing.security.UserContext;



@Service
public class TransportBookingService {



    private final TransportBookingRepository transportBookingRepository;


    private final TransportationRepository transportationRepository;


    private final UserRepository userRepository;


    private final UserContext userContext;







    public TransportBookingService(
            TransportBookingRepository transportBookingRepository,
            TransportationRepository transportationRepository,
            UserRepository userRepository,
            UserContext userContext
    ) {

        this.transportBookingRepository =
                transportBookingRepository;

        this.transportationRepository =
                transportationRepository;

        this.userRepository =
                userRepository;

        this.userContext =
                userContext;

    }









    @Transactional
    public TransportBookingResponse bookTransportation(
            TransportBookingRequest request
    ) {



        User user =
                getLoggedUser();





        if(request.getTransportationId()==null) {

            throw new RuntimeException(
                    "Transportation id is required"
            );

        }






        Transportation transportation =

                transportationRepository.findById(
                        request.getTransportationId()
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Transportation not found"
                        )

                );







        if(request.getSeatsBooked()==null ||
                request.getSeatsBooked()<=0) {


            throw new RuntimeException(
                    "Seats must be greater than zero"
            );

        }







        if(transportation.getAvailableSeats()
                <
                request.getSeatsBooked()) {


            throw new RuntimeException(
                    "Not enough seats available"
            );

        }







        BigDecimal amount =

                transportation.getPrice()

                .multiply(
                        BigDecimal.valueOf(
                                request.getSeatsBooked()
                        )
                );






        transportation.setAvailableSeats(

                transportation.getAvailableSeats()
                -
                request.getSeatsBooked()

        );



        transportationRepository.save(
                transportation
        );







        TransportBooking booking =
                new TransportBooking();



        booking.setUser(
                user
        );


        booking.setTransportation(
                transportation
        );


        booking.setSeatsBooked(
                request.getSeatsBooked()
        );


        booking.setTotalAmount(
                amount
        );


        booking.setStatus(
                BookingStatus.CONFIRMED
        );


        booking.setBookingDate(
                LocalDateTime.now()
        );





        TransportBooking savedBooking =

                transportBookingRepository.save(
                        booking
                );




        return convert(
                savedBooking
        );

    }









    public List<TransportBookingResponse> getMyBookings() {



        User user =
                getLoggedUser();




        return transportBookingRepository
                .findByUserId(
                        user.getId()
                )

                .stream()

                .map(this::convert)

                .collect(Collectors.toList());

    }









    public TransportBookingResponse getBookingById(
            Long bookingId
    ) {



        TransportBooking booking =

                transportBookingRepository.findById(
                        bookingId
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Transport booking not found"
                        )

                );







        if(!booking.getUser()
                .getId()
                .equals(
                        userContext.getUserId()
                )) {


            throw new RuntimeException(
                    "You cannot access this booking"
            );

        }







        return convert(
                booking
        );

    }









    @Transactional
    public void cancelTransportBooking(
            Long bookingId
    ) {



        TransportBooking booking =

                transportBookingRepository.findById(
                        bookingId
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Transport booking not found"
                        )

                );







        if(!booking.getUser()
                .getId()
                .equals(
                        userContext.getUserId()
                )) {


            throw new RuntimeException(
                    "You cannot cancel this booking"
            );

        }






        booking.setStatus(
                BookingStatus.CANCELLED
        );







        Transportation transportation =
                booking.getTransportation();





        transportation.setAvailableSeats(

                transportation.getAvailableSeats()

                +
                booking.getSeatsBooked()

        );





        transportationRepository.save(
                transportation
        );




        transportBookingRepository.save(
                booking
        );


    }









    private User getLoggedUser() {


        if(userContext.getUserId()==null) {


            throw new RuntimeException(
                    "User not authenticated"
            );

        }





        return userRepository.findById(
                userContext.getUserId()
        )

        .orElseThrow(

                () -> new ResourceNotFoundException(
                        "User not found"
                )

        );

    }









    private TransportBookingResponse convert(
            TransportBooking booking
    ) {


        return new TransportBookingResponse(

                booking.getId(),

                booking.getTransportation().getId(),

                booking.getTransportation()
                        .getTransportNumber(),

                booking.getSeatsBooked(),

                booking.getTotalAmount(),

                booking.getStatus(),

                booking.getBookingDate()

        );

    }


}