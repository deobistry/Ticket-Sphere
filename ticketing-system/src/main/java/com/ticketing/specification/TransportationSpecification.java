package com.ticketing.specification;


import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.Specification;


import com.ticketing.dto.request.TransportationSearchRequest;
import com.ticketing.entity.Transportation;
import com.ticketing.enums.TransportType;


import jakarta.persistence.criteria.Predicate;



public class TransportationSpecification {



    public static Specification<Transportation> filter(
            TransportationSearchRequest request
    ) {


        return (root, query, criteriaBuilder) -> {



            List<Predicate> predicates =
                    new ArrayList<>();





            if(request.getSource() != null &&
                    !request.getSource().isEmpty()) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("source"),
                                request.getSource()
                        )
                );

            }






            if(request.getDestination() != null &&
                    !request.getDestination().isEmpty()) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("destination"),
                                request.getDestination()
                        )
                );

            }







            if(request.getType() != null &&
                    !request.getType().isEmpty()) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("type"),
                                TransportType.valueOf(
                                        request.getType()
                                                .toUpperCase()
                                )
                        )
                );

            }







            if(request.getTravelDate() != null) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("travelDate"),
                                request.getTravelDate()
                        )
                );

            }







            if(request.getMinPrice() != null) {


                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("price"),
                                request.getMinPrice()
                        )
                );

            }







            if(request.getMaxPrice() != null) {


                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("price"),
                                request.getMaxPrice()
                        )
                );

            }







            if(request.getMinSeats() != null) {


                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("availableSeats"),
                                request.getMinSeats()
                        )
                );

            }







            return criteriaBuilder.and(
                    predicates.toArray(
                            new Predicate[0]
                    )
            );

        };

    }

}