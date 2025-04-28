package com.tripexpense.repository;

import com.tripexpense.dto.CityDTO;
import com.tripexpense.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCity(CityDTO cityDTO);
    List<Hotel> findByPricePerNightBetween(Double min, Double max);
    List<Hotel> findByRatingGreaterThanEqual(Integer rating);
}
