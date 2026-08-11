package com.ticketing.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticketing.entity.Accommodation;
import com.ticketing.enums.AccommodationType;



public interface AccommodationRepository 
        extends JpaRepository<Accommodation, Long>,
                JpaSpecificationExecutor<Accommodation> {



    @Query("""
            SELECT a FROM Accommodation a
            WHERE (:city IS NULL OR LOWER(a.city) = LOWER(:city))
            AND (:type IS NULL OR a.type = :type)
            """)
    List<Accommodation> searchAccommodation(
            @Param("city") String city,
            @Param("type") AccommodationType type
    );

}