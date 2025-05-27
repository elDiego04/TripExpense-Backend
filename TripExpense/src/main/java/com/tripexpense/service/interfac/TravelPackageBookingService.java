package com.tripexpense.service;

import com.tripexpense.dto.TravelPackageBookingDTO;
import java.util.List;

public interface TravelPackageBookingService {

    TravelPackageBookingDTO createBooking(TravelPackageBookingDTO bookingDTO);

    List<TravelPackageBookingDTO> getAllBookings();

    TravelPackageBookingDTO getBookingById(Long id);

    TravelPackageBookingDTO updateBooking(Long id, TravelPackageBookingDTO bookingDTO);

    void deleteBooking(Long id);

    List<TravelPackageBookingDTO> getBookingsByUserId(Long userId);
}
