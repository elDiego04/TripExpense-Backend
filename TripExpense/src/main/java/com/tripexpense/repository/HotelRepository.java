package com.tripexpense.repository;

import com.tripexpense.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCityCityId(Long cityId);
    List<Hotel> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);

    boolean existsByNameAndCityCityId(String name, Long cityId);
}
