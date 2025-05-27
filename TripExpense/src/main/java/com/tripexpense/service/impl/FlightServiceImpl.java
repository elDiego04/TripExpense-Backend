package com.tripexpense.service.impl;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.Flight;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.FlightRepository;
import com.tripexpense.service.interfac.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    private FlightDTO mapToDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(flight.getFlightId());
        dto.setAirline(flight.getAirline());
        dto.setAirlineLogoUrl(flight.getAirlineLogoUrl());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureCityId(flight.getDepartureCity().getCityId());
        dto.setArrivalCityId(flight.getArrivalCity().getCityId());
        dto.setDepartureDateTime(flight.getDepartureDateTime());
        dto.setArrivalDateTime(flight.getArrivalDateTime());
        dto.setDurationMinutes(flight.getDurationMinutes());
        return dto;
    }

    private Flight mapToEntity(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setFlightId(dto.getFlightId());
        flight.setAirline(dto.getAirline());
        flight.setAirlineLogoUrl(dto.getAirlineLogoUrl());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDepartureDateTime(dto.getDepartureDateTime());
        flight.setArrivalDateTime(dto.getArrivalDateTime());
        flight.setDurationMinutes(dto.getDurationMinutes());

        City departureCity = cityRepository.findById(dto.getDepartureCityId()).orElse(null);
        City arrivalCity = cityRepository.findById(dto.getArrivalCityId()).orElse(null);

        flight.setDepartureCity(departureCity);
        flight.setArrivalCity(arrivalCity);
        return flight;
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = mapToEntity(flightDTO);
        return mapToDTO(flightRepository.save(flight));
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return flightRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isEmpty()) return null;

        Flight flight = optionalFlight.get();
        flight.setAirline(flightDTO.getAirline());
        flight.setAirlineLogoUrl(flightDTO.getAirlineLogoUrl());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDateTime(flightDTO.getDepartureDateTime());
        flight.setArrivalDateTime(flightDTO.getArrivalDateTime());
        flight.setDurationMinutes(flightDTO.getDurationMinutes());

        flight.setDepartureCity(cityRepository.findById(flightDTO.getDepartureCityId()).orElse(null));
        flight.setArrivalCity(cityRepository.findById(flightDTO.getArrivalCityId()).orElse(null));

        return mapToDTO(flightRepository.save(flight));
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}