package com.tripexpense.repository;

import com.tripexpense.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByDepartureCityCityIdAndArrivalCityCityIdAndDepartureDateTimeBetween(
            Long departureCityId,
            Long arrivalCityId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime);

    @Query("SELECT f FROM Flight f WHERE DATE(f.departureDateTime) BETWEEN :startDate AND :endDate")
    List<Flight> findByDepartureDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}