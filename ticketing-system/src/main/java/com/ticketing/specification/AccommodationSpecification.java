package com.ticketing.specification;


import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.Specification;


import com.ticketing.dto.request.AccommodationSearchRequest;
import com.ticketing.entity.Accommodation;


import jakarta.persistence.criteria.Predicate;



public class AccommodationSpecification {



    public static Specification<Accommodation> filter(
            AccommodationSearchRequest request
    ) {


        return (root, query, criteriaBuilder) -> {


            List<Predicate> predicates =
                    new ArrayList<>();





            if(request.getCity() != null &&
                    !request.getCity().isEmpty()) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("city"),
                                request.getCity()
                        )
                );

            }







            if(request.getType() != null) {


                predicates.add(
                        criteriaBuilder.equal(
                                root.get("type"),
                                request.getType()
                        )
                );

            }







            if(request.getMinPrice() != null) {


                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("pricePerNight"),
                                request.getMinPrice()
                        )
                );

            }







            if(request.getMaxPrice() != null) {


                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("pricePerNight"),
                                request.getMaxPrice()
                        )
                );

            }







            if(request.getMinRating() != null) {


                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("rating"),
                                request.getMinRating()
                        )
                );

            }







            if(request.getMinRooms() != null) {


                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("availableRooms"),
                                request.getMinRooms()
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