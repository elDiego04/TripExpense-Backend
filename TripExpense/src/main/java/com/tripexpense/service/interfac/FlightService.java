package com.tripexpense.service.interfac;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.dto.FlightSearchDTO;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    FlightDTO getFlightById(Long id);
    List<FlightDTO> getAllFlights();
    List<FlightDTO> searchFlights(FlightSearchDTO searchDTO);

    List<FlightDTO> findFlightsByCitiesAndDate(Long departureCityId, Long arrivalCityId, LocalDate date);
    FlightDTO updateFlight(Long id, FlightDTO flightDTO);


    void deleteFlight(Long id);
}