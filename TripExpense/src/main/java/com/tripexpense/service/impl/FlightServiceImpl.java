package com.tripexpense.service.impl;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.dto.FlightSearchDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.Flight;
import com.tripexpense.exception.DuplicateResourceException;
import com.tripexpense.exception.InvalidRequestException;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.FlightRepository;
import com.tripexpense.service.interfac.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public FlightDTO createFlight(FlightDTO flightDTO) {
        flightDTO.validate();

        if (flightRepository.existsByFlightNumber(flightDTO.getFlightNumber())) {
            throw new DuplicateResourceException("Ya existe un vuelo con este número");
        }

        City departureCity = cityRepository.findById(flightDTO.getDepartureCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad de origen no encontrada"));

        City arrivalCity = cityRepository.findById(flightDTO.getArrivalCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad de destino no encontrada"));

        if (departureCity.getCityId().equals(arrivalCity.getCityId())) {
            throw new InvalidRequestException("La ciudad de origen y destino no pueden ser iguales");
        }

        if (!flightDTO.getArrivalDateTime().isAfter(flightDTO.getDepartureDateTime())) {
            throw new InvalidRequestException("La fecha/hora de llegada debe ser posterior a la de salida");
        }

        Flight flight = new Flight();
        mapDTOToEntity(flightDTO, flight);
        flight.setDepartureCity(departureCity);
        flight.setArrivalCity(arrivalCity);

        Flight savedFlight = flightRepository.save(flight);
        return convertToFullDTO(savedFlight);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vuelo no encontrado con ID: " + id));
        return convertToFullDTO(flight);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vuelo no encontrado con ID: " + id));

        if (!existingFlight.getFlightNumber().equals(flightDTO.getFlightNumber())) {
            if (flightRepository.existsByFlightNumber(flightDTO.getFlightNumber())) {
                throw new DuplicateResourceException("Ya existe un vuelo con este número");
            }
        }

        if (flightDTO.getDepartureDateTime() != null && flightDTO.getArrivalDateTime() != null) {
            if (!flightDTO.getArrivalDateTime().isAfter(flightDTO.getDepartureDateTime())) {
                throw new InvalidRequestException("La fecha/hora de llegada debe ser posterior a la de salida");
            }
        }

        if (flightDTO.getDepartureCityId() != null &&
                !flightDTO.getDepartureCityId().equals(existingFlight.getDepartureCity().getCityId())) {
            City newDepartureCity = cityRepository.findById(flightDTO.getDepartureCityId())
                    .orElseThrow(() -> new NoSuchElementException("Ciudad de origen no encontrada"));
            existingFlight.setDepartureCity(newDepartureCity);
        }

        if (flightDTO.getArrivalCityId() != null &&
                !flightDTO.getArrivalCityId().equals(existingFlight.getArrivalCity().getCityId())) {
            City newArrivalCity = cityRepository.findById(flightDTO.getArrivalCityId())
                    .orElseThrow(() -> new NoSuchElementException("Ciudad de destino no encontrada"));
            existingFlight.setArrivalCity(newArrivalCity);
        }

        if (existingFlight.getDepartureCity().getCityId().equals(existingFlight.getArrivalCity().getCityId())) {
            throw new InvalidRequestException("La ciudad de origen y destino no pueden ser iguales");
        }

        mapDTOToEntity(flightDTO, existingFlight);

        Flight updatedFlight = flightRepository.save(existingFlight);
        return convertToFullDTO(updatedFlight);
    }
    @Override
    public List<FlightDTO> searchFlights(FlightSearchDTO searchDTO) {
        LocalDate departureDate = searchDTO.getDepartureDate();
        LocalDateTime startDateTime = departureDate.atStartOfDay();
        LocalDateTime endDateTime = departureDate.plusDays(1).atStartOfDay();

        List<Flight> flights = flightRepository.findByDepartureCityCityIdAndArrivalCityCityIdAndDepartureDateTimeBetween(
                searchDTO.getDepartureCityId(),
                searchDTO.getArrivalCityId(),
                startDateTime,
                endDateTime);

        return flights.stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new NoSuchElementException("Vuelo no encontrado con ID: " + id);
        }
        flightRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDTO> findFlightsByCitiesAndDate(Long departureCityId, Long arrivalCityId, LocalDate date) {
        if (!cityRepository.existsById(departureCityId)) {
            throw new NoSuchElementException("Ciudad de origen no encontrada");
        }
        if (!cityRepository.existsById(arrivalCityId)) {
            throw new NoSuchElementException("Ciudad de destino no encontrada");
        }

        return flightRepository.findByDepartureCityCityIdAndArrivalCityCityIdAndDepartureDateTimeBetween(
                        departureCityId,
                        arrivalCityId,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay())
                .stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }


    private void mapDTOToEntity(FlightDTO dto, Flight entity) {
        if (dto.getAirline() != null) entity.setAirline(dto.getAirline());
        if (dto.getAirlineLogoUrl() != null) entity.setAirlineLogoUrl(dto.getAirlineLogoUrl());
        if (dto.getFlightNumber() != null) entity.setFlightNumber(dto.getFlightNumber());
        if (dto.getDepartureDateTime() != null) entity.setDepartureDateTime(dto.getDepartureDateTime());
        if (dto.getArrivalDateTime() != null) entity.setArrivalDateTime(dto.getArrivalDateTime());
        if (dto.getDurationMinutes() != null) entity.setDurationMinutes(dto.getDurationMinutes());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getAdults() != null) entity.setAdults(dto.getAdults());
        if (dto.getChildren() != null) entity.setChildren(dto.getChildren());
        if (dto.getFlightClass() != null) entity.setFlightClass(dto.getFlightClass());
    }

    private FlightDTO convertToFullDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(flight.getFlightId());
        dto.setAirline(flight.getAirline());
        dto.setAirlineLogoUrl(flight.getAirlineLogoUrl());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureCityId(flight.getDepartureCity().getCityId());
        dto.setDepartureCityName(flight.getDepartureCity().getName());
        dto.setArrivalCityId(flight.getArrivalCity().getCityId());
        dto.setArrivalCityName(flight.getArrivalCity().getName());
        dto.setDepartureDateTime(flight.getDepartureDateTime());
        dto.setArrivalDateTime(flight.getArrivalDateTime());
        dto.setDurationMinutes(flight.getDurationMinutes());
        dto.setPrice(flight.getPrice());
        dto.setAdults(flight.getAdults());
        dto.setChildren(flight.getChildren());
        dto.setFlightClass(flight.getFlightClass());
        return dto;
    }
}