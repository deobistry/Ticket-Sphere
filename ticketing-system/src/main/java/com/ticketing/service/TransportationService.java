package com.ticketing.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ticketing.dto.request.TransportationRequest;
import com.ticketing.dto.request.TransportationSearchRequest;
import com.ticketing.dto.response.TransportationResponse;
import com.ticketing.entity.Transportation;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.TransportationRepository;



@Service
public class TransportationService {



    private final TransportationRepository transportationRepository;





    public TransportationService(
            TransportationRepository transportationRepository
    ) {

        this.transportationRepository =
                transportationRepository;

    }









    @Transactional
    public void createTransportation(
    		
            TransportationRequest request
    ) {


        if(request.getTransportNumber()==null ||
                request.getTransportNumber().isBlank()) {


            throw new RuntimeException(
                    "Transport number is required"
            );

        }





        Transportation transportation =
                new Transportation();



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


        transportation.setAvailableSeats(
                request.getAvailableSeats()
        );


        transportation.setPrice(
                request.getPrice()
        );




        transportationRepository.save(
                transportation
        );


    }









    public List<TransportationResponse> getAllTransportation() {


        return transportationRepository
                .findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }









    public TransportationResponse getTransportationById(
            Long id
    ) {


        Transportation transportation =

                transportationRepository
                        .findById(id)

                        .orElseThrow(

                                () -> new ResourceNotFoundException(
                                        "Transportation not found"
                                )

                        );



        return convert(
                transportation
        );

    }









    public List<TransportationResponse> searchTransportation(
            TransportationSearchRequest request
    ) {


    	Specification<Transportation> specification =
    	        (root, query, criteriaBuilder) -> null;




        if(request.getSource()!=null &&
                !request.getSource().isBlank()) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.equal(

                                            cb.lower(
                                                    root.get("source")
                                            ),

                                            request.getSource()
                                                    .toLowerCase()

                                    )

                    );

        }






        if(request.getDestination()!=null &&
                !request.getDestination().isBlank()) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.equal(

                                            cb.lower(
                                                    root.get("destination")
                                            ),

                                            request.getDestination()
                                                    .toLowerCase()

                                    )

                    );

        }







        if(request.getType()!=null &&
                !request.getType().isBlank()) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.equal(

                                            root.get("type"),

                                            request.getType()

                                    )

                    );

        }







        if(request.getTravelDate()!=null) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.equal(

                                            cb.function(
                                                    "DATE",
                                                    LocalDate.class,
                                                    root.get("departureTime")
                                            ),

                                            request.getTravelDate()

                                    )

                    );

        }







        if(request.getMinPrice()!=null) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.greaterThanOrEqualTo(

                                            root.get("price"),

                                            request.getMinPrice()

                                    )

                    );

        }







        if(request.getMaxPrice()!=null) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.lessThanOrEqualTo(

                                            root.get("price"),

                                            request.getMaxPrice()

                                    )

                    );

        }







        if(request.getMinSeats()!=null) {


            specification =
                    specification.and(

                            (root, query, cb) ->

                                    cb.greaterThanOrEqualTo(

                                            root.get("availableSeats"),

                                            request.getMinSeats()

                                    )

                    );

        }








        return transportationRepository
                .findAll(specification)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }









    private TransportationResponse convert(
            Transportation transportation
    ) {


        return new TransportationResponse(

                transportation.getId(),

                transportation.getTransportNumber(),

                transportation.getType(),

                transportation.getOperatorName(),

                transportation.getSource(),

                transportation.getDestination(),

                transportation.getDepartureTime(),

                transportation.getArrivalTime(),

                transportation.getDuration(),

                transportation.getTotalSeats(),

                transportation.getAvailableSeats(),

                transportation.getPrice()

        );

    }

    @Transactional
    public void deleteTransportation(
            Long id
    ) {


        Transportation transportation =

                transportationRepository
                        .findById(id)

                        .orElseThrow(

                                () -> new ResourceNotFoundException(
                                        "Transportation not found"
                                )

                        );



        transportationRepository.delete(
                transportation
        );


    }

}