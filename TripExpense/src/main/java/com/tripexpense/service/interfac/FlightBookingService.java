package com.tripexpense.service;

import com.tripexpense.dto.FlightBookingDTO;

import java.util.List;

public interface FlightBookingService {
    FlightBookingDTO createFlightBooking(FlightBookingDTO bookingDTO);
    List<FlightBookingDTO> getAllFlightBookings();
    FlightBookingDTO getFlightBookingById(Long id);
    List<FlightBookingDTO> getFlightBookingsByUserId(Long userId);
    void deleteFlightBooking(Long id);
}
