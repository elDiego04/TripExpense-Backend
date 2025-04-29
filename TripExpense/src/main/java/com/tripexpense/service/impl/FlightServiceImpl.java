package com.tripexpense.service.impl;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.Flight;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.FlightRepository;
import com.tripexpense.service.interfac.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        if (flightRepository.existsByFlightNumber(flightDTO.getFlightNumber())) {
            throw new RuntimeException("Flight number already exists");
        }

        City departureCity = cityRepository.findById(flightDTO.getDepartureCityId())
                .orElseThrow(() -> new RuntimeException("Departure city not found"));

        City arrivalCity = cityRepository.findById(flightDTO.getArrivalCityId())
                .orElseThrow(() -> new RuntimeException("Arrival city not found"));

        Flight flight = new Flight();
        flight.setAirline(flightDTO.getAirline());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureCity(departureCity);
        flight.setArrivalCity(arrivalCity);
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setDuration(flightDTO.getDuration());
        flight.setPrice(flightDTO.getPrice());
        flight.setAdultNumber(flightDTO.getAdultNumber());
        flight.setChildNumber(flightDTO.getChildNumber());
        flight.setFlightClass(flightDTO.getFlightClass());

        Flight savedFlight = flightRepository.save(flight);
        return convertToDTO(savedFlight);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
        return convertToDTO(flight);
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<FlightDTO> searchFlights(FlightSearchDTO searchDTO) {
        return flightRepository.findByDepartureCityCityIdAndArrivalCityCityIdAndDepartureDate(
                        searchDTO.getDepartureCityId(),
                        searchDTO.getArrivalCityId(),
                        searchDTO.getDepartureDate()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }*/

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        if (flightDTO.getAirline() != null) existingFlight.setAirline(flightDTO.getAirline());
        if (flightDTO.getFlightNumber() != null) existingFlight.setFlightNumber(flightDTO.getFlightNumber());
        if (flightDTO.getDepartureDate() != null) existingFlight.setDepartureDate(flightDTO.getDepartureDate());
        if (flightDTO.getDepartureTime() != null) existingFlight.setDepartureTime(flightDTO.getDepartureTime());
        if (flightDTO.getArrivalTime() != null) existingFlight.setArrivalTime(flightDTO.getArrivalTime());
        if (flightDTO.getDuration() != null) existingFlight.setDuration(flightDTO.getDuration());
        if (flightDTO.getPrice() != null) existingFlight.setPrice(flightDTO.getPrice());
        if (flightDTO.getAdultNumber() != null) existingFlight.setAdultNumber(flightDTO.getAdultNumber());
        if (flightDTO.getChildNumber() != null) existingFlight.setChildNumber(flightDTO.getChildNumber());
        if (flightDTO.getFlightClass() != null) existingFlight.setFlightClass(flightDTO.getFlightClass());

        if (flightDTO.getDepartureCityId() != null &&
                !flightDTO.getDepartureCityId().equals(existingFlight.getDepartureCity().getCityId())) {
            City newDepartureCity = cityRepository.findById(flightDTO.getDepartureCityId())
                    .orElseThrow(() -> new RuntimeException("Departure city not found"));
            existingFlight.setDepartureCity(newDepartureCity);
        }

        if (flightDTO.getArrivalCityId() != null &&
                !flightDTO.getArrivalCityId().equals(existingFlight.getArrivalCity().getCityId())) {
            City newArrivalCity = cityRepository.findById(flightDTO.getArrivalCityId())
                    .orElseThrow(() -> new RuntimeException("Arrival city not found"));
            existingFlight.setArrivalCity(newArrivalCity);
        }

        Flight updatedFlight = flightRepository.save(existingFlight);
        return convertToDTO(updatedFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDTO> findFlightsByCitiesAndDate(Long departureCityId, Long arrivalCityId, LocalDate date) {
        return null;
    }

    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(flight.getFlightId());
        dto.setAirline(flight.getAirline());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureCityId(flight.getDepartureCity().getCityId());
        dto.setDepartureCityName(flight.getDepartureCity().getName());
        dto.setArrivalCityId(flight.getArrivalCity().getCityId());
        dto.setArrivalCityName(flight.getArrivalCity().getName());
        dto.setDepartureDate(flight.getDepartureDate());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setDuration(flight.getDuration());
        dto.setPrice(flight.getPrice());
        dto.setAdultNumber(flight.getAdultNumber());
        dto.setChildNumber(flight.getChildNumber());
        dto.setFlightClass(flight.getFlightClass());
        return dto;
    }
}
