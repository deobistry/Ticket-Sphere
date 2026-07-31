package com.ticketing.service.admin;


import java.util.List;


import org.springframework.stereotype.Service;


import com.ticketing.dto.admin.AdminAccommodationRequest;
import com.ticketing.entity.Accommodation;
import com.ticketing.repository.AccommodationRepository;



@Service
public class AdminAccommodationService {



    private final AccommodationRepository accommodationRepository;





    public AdminAccommodationService(
            AccommodationRepository accommodationRepository
    ) {

        this.accommodationRepository =
                accommodationRepository;

    }









    public void createAccommodation(
            AdminAccommodationRequest request
    ) {



        Accommodation accommodation =
                new Accommodation();



        mapToEntity(
                accommodation,
                request
        );



        accommodationRepository.save(
                accommodation
        );

    }









    public List<Accommodation> getAllAccommodation() {


        return accommodationRepository.findAll();

    }









    public Accommodation getById(
            Long id
    ) {


        return accommodationRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Accommodation not found"
                        )
                );

    }









    public void updateAccommodation(
            Long id,
            AdminAccommodationRequest request
    ) {



        Accommodation accommodation =
                getById(id);



        mapToEntity(
                accommodation,
                request
        );



        accommodationRepository.save(
                accommodation
        );

    }









    public void deleteAccommodation(
            Long id
    ) {



        Accommodation accommodation =
                getById(id);



        accommodationRepository.delete(
                accommodation
        );

    }









    private void mapToEntity(
            Accommodation accommodation,
            AdminAccommodationRequest request
    ) {



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


    }


}