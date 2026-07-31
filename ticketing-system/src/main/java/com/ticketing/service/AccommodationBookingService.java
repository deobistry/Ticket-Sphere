package com.ticketing.service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ticketing.dto.request.AccommodationBookingRequest;
import com.ticketing.dto.response.AccommodationBookingResponse;
import com.ticketing.entity.Accommodation;
import com.ticketing.entity.AccommodationBooking;
import com.ticketing.entity.User;
import com.ticketing.enums.BookingStatus;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.AccommodationBookingRepository;
import com.ticketing.repository.AccommodationRepository;
import com.ticketing.repository.UserRepository;
import com.ticketing.security.UserContext;



@Service
public class AccommodationBookingService {



    private final AccommodationBookingRepository accommodationBookingRepository;


    private final AccommodationRepository accommodationRepository;


    private final UserRepository userRepository;


    private final UserContext userContext;







    public AccommodationBookingService(
            AccommodationBookingRepository accommodationBookingRepository,
            AccommodationRepository accommodationRepository,
            UserRepository userRepository,
            UserContext userContext
    ) {

        this.accommodationBookingRepository =
                accommodationBookingRepository;

        this.accommodationRepository =
                accommodationRepository;

        this.userRepository =
                userRepository;

        this.userContext =
                userContext;

    }









    @Transactional
    public AccommodationBookingResponse bookAccommodation(
            AccommodationBookingRequest request
    ) {



        if(request.getAccommodationId()==null) {

            throw new RuntimeException(
                    "Accommodation id is required"
            );

        }




        if(request.getRoomsBooked()==null ||
                request.getRoomsBooked()<=0) {


            throw new RuntimeException(
                    "Rooms booked must be greater than zero"
            );

        }





        User user =
                getLoggedUser();






        Accommodation accommodation =

                accommodationRepository.findById(
                        request.getAccommodationId()
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Accommodation not found"
                        )

                );







        if(accommodation.getAvailableRooms()
                < request.getRoomsBooked()) {


            throw new RuntimeException(
                    "Not enough rooms available"
            );

        }






        if(request.getCheckInDate()
                .isAfter(
                        request.getCheckOutDate()
                )) {


            throw new RuntimeException(
                    "Invalid booking dates"
            );

        }







        long nights =

                ChronoUnit.DAYS.between(

                        request.getCheckInDate(),

                        request.getCheckOutDate()

                );






        if(nights<=0) {


            throw new RuntimeException(
                    "Minimum stay should be one night"
            );

        }







        BigDecimal totalAmount =

                accommodation.getPricePerNight()

                .multiply(
                        BigDecimal.valueOf(nights)
                )

                .multiply(
                        BigDecimal.valueOf(
                                request.getRoomsBooked()
                        )
                );








        accommodation.setAvailableRooms(

                accommodation.getAvailableRooms()

                -
                request.getRoomsBooked()

        );



        accommodationRepository.save(
                accommodation
        );








        AccommodationBooking booking =
                new AccommodationBooking();



        booking.setUser(
                user
        );



        booking.setAccommodation(
                accommodation
        );



        booking.setRoomsBooked(
                request.getRoomsBooked()
        );



        booking.setCheckInDate(
                request.getCheckInDate()
        );



        booking.setCheckOutDate(
                request.getCheckOutDate()
        );



        booking.setTotalAmount(
                totalAmount
        );



        booking.setStatus(
                BookingStatus.CONFIRMED
        );



        booking.setBookingDate(
                LocalDateTime.now()
        );






        AccommodationBooking savedBooking =

                accommodationBookingRepository.save(
                        booking
                );





        return convert(
                savedBooking
        );


    }









    public List<AccommodationBookingResponse> getMyBookings() {



        User user =
                getLoggedUser();




        return accommodationBookingRepository
                .findByUserId(
                        user.getId()
                )

                .stream()

                .map(this::convert)

                .collect(Collectors.toList());

    }









    public AccommodationBookingResponse getBookingById(
            Long bookingId
    ) {



        AccommodationBooking booking =

                accommodationBookingRepository
                .findById(
                        bookingId
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Accommodation booking not found"
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
    public void cancelAccommodationBooking(
            Long bookingId
    ) {



        AccommodationBooking booking =

                accommodationBookingRepository
                .findById(
                        bookingId
                )

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Accommodation booking not found"
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







        Accommodation accommodation =
                booking.getAccommodation();




        accommodation.setAvailableRooms(

                accommodation.getAvailableRooms()

                +
                booking.getRoomsBooked()

        );




        accommodationRepository.save(
                accommodation
        );




        accommodationBookingRepository.save(
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









    private AccommodationBookingResponse convert(
            AccommodationBooking booking
    ) {



        return new AccommodationBookingResponse(

                booking.getId(),

                booking.getAccommodation().getId(),

                booking.getAccommodation().getName(),

                booking.getRoomsBooked(),

                booking.getCheckInDate(),

                booking.getCheckOutDate(),

                booking.getTotalAmount(),

                booking.getStatus(),

                booking.getBookingDate()

        );


    }


}