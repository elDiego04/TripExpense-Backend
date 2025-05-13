package com.tripexpense.repository;

import com.tripexpense.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCountryContainingIgnoreCase(String country);
    List<City> findByNameContainingIgnoreCase(String name);

    boolean existsByNameAndCountry(String name,  String country);


    Optional<City> findByName(String name);
}
