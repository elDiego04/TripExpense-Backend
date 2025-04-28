package com.tripexpense.repository;

import com.tripexpense.dto.CityDTO;
import com.tripexpense.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByCity(CityDTO cityDTO);
    List<Activity> findByCategory(String category);
    List<Activity> findByPriceLessThanEqual(Double price);

}
