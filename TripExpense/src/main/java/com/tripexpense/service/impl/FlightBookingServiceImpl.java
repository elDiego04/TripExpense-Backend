package com.tripexpense.service.impl;

import com.tripexpense.dto.FlightBookingDTO;
import com.tripexpense.entity.Flight;
import com.tripexpense.entity.FlightBooking;
import com.tripexpense.entity.User;
import com.tripexpense.exception.ResourceNotFoundException;
import com.tripexpense.repository.FlightBookingRepository;
import com.tripexpense.repository.FlightRepository;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {

    @Autowired
    private FlightBookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    private FlightBookingDTO mapToDTO(FlightBooking booking) {
        FlightBookingDTO dto = new FlightBookingDTO();
        dto.setId(booking.getId());
        dto.setFlightId(booking.getFlight().getFlightId());
        dto.setUserId(booking.getUser().getId());
        dto.setPrice(booking.getPrice());
        dto.setAdults(booking.getAdults());
        dto.setChildren(booking.getChildren());
        dto.setFlightClass(booking.getFlightClass());
        return dto;
    }

    private FlightBooking mapToEntity(FlightBookingDTO dto) {
        Flight flight = flightRepository.findById(dto.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Vuelo no encontrado con ID: " + dto.getFlightId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUserId()));

        FlightBooking booking = new FlightBooking();
        booking.setId(dto.getId());
        booking.setFlight(flight);
        booking.setUser(user);
        booking.setPrice(dto.getPrice());
        booking.setAdults(dto.getAdults());
        booking.setChildren(dto.getChildren());
        booking.setFlightClass(dto.getFlightClass());
        return booking;
    }

    @Override
    public FlightBookingDTO createFlightBooking(FlightBookingDTO dto) {
        FlightBooking booking = mapToEntity(dto);
        return mapToDTO(bookingRepository.save(booking));
    }

    @Override
    public List<FlightBookingDTO> getAllFlightBookings() {
        return bookingRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public FlightBookingDTO getFlightBookingById(Long id) {
        FlightBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de vuelo no encontrada con ID: " + id));
        return mapToDTO(booking);
    }

    @Override
    public List<FlightBookingDTO> getFlightBookingsByUserId(Long userId) {
        List<FlightBooking> bookings = bookingRepository.findByUser_Id(userId);
        return bookings.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteFlightBooking(Long id) {
        FlightBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
        bookingRepository.delete(booking);
    }
}
