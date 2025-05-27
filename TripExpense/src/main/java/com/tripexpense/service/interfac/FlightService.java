package com.tripexpense.service.interfac;

import com.tripexpense.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    FlightDTO getFlightById(Long id);
    List<FlightDTO> getAllFlights();
    FlightDTO updateFlight(Long id, FlightDTO flightDTO);


    void deleteFlight(Long id);
}