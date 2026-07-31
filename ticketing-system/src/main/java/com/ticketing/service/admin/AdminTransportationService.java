package com.ticketing.service.admin;


import java.util.List;


import org.springframework.stereotype.Service;


import com.ticketing.dto.admin.AdminTransportationRequest;
import com.ticketing.entity.Transportation;
import com.ticketing.repository.TransportationRepository;



@Service
public class AdminTransportationService {



    private final TransportationRepository transportationRepository;





    public AdminTransportationService(
            TransportationRepository transportationRepository
    ) {

        this.transportationRepository = transportationRepository;

    }








    public void createTransportation(
            AdminTransportationRequest request
    ) {



        Transportation transportation =
                new Transportation();



        mapToEntity(
                transportation,
                request
        );



        transportation.setAvailableSeats(
                request.getTotalSeats()
        );



        transportationRepository.save(
                transportation
        );

    }









    public List<Transportation> getAllTransportation() {


        return transportationRepository.findAll();

    }









    public Transportation getById(
            Long id
    ) {


        return transportationRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Transportation not found"
                        )
                );

    }









    public void updateTransportation(
            Long id,
            AdminTransportationRequest request
    ) {



        Transportation transportation =
                getById(id);



        mapToEntity(
                transportation,
                request
        );



        transportationRepository.save(
                transportation
        );

    }









    public void deleteTransportation(
            Long id
    ) {



        Transportation transportation =
                getById(id);



        transportationRepository.delete(
                transportation
        );

    }








    private void mapToEntity(
            Transportation transportation,
            AdminTransportationRequest request
    ) {



        transportation.setTransportNumber(
                request.getTransportNumber()
        );


        transportation.setType(
                request.getType()
        );


        transportation.setOperatorName(
                request.getOperatorName()
        );


        transportation.setSource(
                request.getSource()
        );


        transportation.setDestination(
                request.getDestination()
        );


        transportation.setDepartureTime(
                request.getDepartureTime()
        );


        transportation.setArrivalTime(
                request.getArrivalTime()
        );


        transportation.setDuration(
                request.getDuration()
        );


        transportation.setTotalSeats(
                request.getTotalSeats()
        );


        transportation.setPrice(
                request.getPrice()
        );

    }


}