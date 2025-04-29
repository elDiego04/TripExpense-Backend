package com.tripexpense.repository;

import com.tripexpense.dto.CityDTO;
import com.tripexpense.entity.Flight;
import com.tripexpense.enums.FlightClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureCityAndArrivalCityAndDepartureDateAndReturnDate(
            CityDTO departureCity, CityDTO arrivalCity,
            LocalDate departureDate, LocalDate returnDate
    );

    List<Flight> findByDepartureDateBetweenAndFlightClass(
            LocalDate startDate,
            LocalDate endDate,
            FlightClass flightClass);

    boolean existsByFlightNumber(String flightNumber);
}
