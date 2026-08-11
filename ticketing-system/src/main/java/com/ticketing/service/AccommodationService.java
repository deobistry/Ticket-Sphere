package com.ticketing.service;


import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ticketing.dto.request.AccommodationRequest;
import com.ticketing.dto.request.AccommodationSearchRequest;
import com.ticketing.dto.response.AccommodationResponse;
import com.ticketing.entity.Accommodation;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.AccommodationRepository;



@Service
public class AccommodationService {



    private final AccommodationRepository accommodationRepository;





    public AccommodationService(
            AccommodationRepository accommodationRepository
    ) {

        this.accommodationRepository =
                accommodationRepository;

    }









    public void createAccommodation(
            AccommodationRequest request
    ) {



        if(request.getName()==null ||
                request.getName().isEmpty()) {


            throw new RuntimeException(
                    "Accommodation name is required"
            );

        }





        Accommodation accommodation =
                new Accommodation();



        accommodation.setName(
                request.getName()
        );


        accommodation.setType(
                request.getType()
        );


        accommodation.setCity(
                request.getCity()
        );


        accommodation.setAddress(
                request.getAddress()
        );


        accommodation.setDescription(
                request.getDescription()
        );


        accommodation.setRating(
                request.getRating()
        );


        accommodation.setPricePerNight(
                request.getPricePerNight()
        );


        accommodation.setAvailableRooms(
                request.getAvailableRooms()
        );





        accommodationRepository.save(
                accommodation
        );

    }









    public List<AccommodationResponse> getAllAccommodation() {



        return accommodationRepository.findAll()

                .stream()

                .map(this::convert)

                .collect(Collectors.toList());

    }









    public AccommodationResponse getAccommodationById(
            Long id
    ) {



        Accommodation accommodation =
                accommodationRepository.findById(id)

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Accommodation not found"
                        )

                );



        return convert(accommodation);

    }









    public List<AccommodationResponse> searchAccommodation(
            AccommodationSearchRequest request
    ) {



        return accommodationRepository
                .searchAccommodation(
                        request.getCity(),
                        request.getType()
                )

                .stream()

                .map(this::convert)

                .collect(Collectors.toList());

    }









    @Transactional
    public void deleteAccommodation(
            Long id
    ){



        Accommodation accommodation =

                accommodationRepository.findById(id)

                .orElseThrow(

                        () -> new ResourceNotFoundException(
                                "Accommodation not found"
                        )

                );





        /*
         * Remove bookings first
         * to avoid foreign key constraint issues
         */

        accommodation.getBookings()
                .clear();





        accommodationRepository.delete(
                accommodation
        );


    }









    private AccommodationResponse convert(
            Accommodation accommodation
    ) {



        return new AccommodationResponse(

                accommodation.getId(),

                accommodation.getName(),

                accommodation.getType(),

                accommodation.getCity(),

                accommodation.getAddress(),

                accommodation.getDescription(),

                accommodation.getRating(),

                accommodation.getPricePerNight(),

                accommodation.getAvailableRooms()

        );


    }
    

}