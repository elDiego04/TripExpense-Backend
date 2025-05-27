package com.tripexpense.service.interfac;

import com.tripexpense.dto.ActivityBookingDTO;

import java.util.List;

public interface ActivityBookingService {

    ActivityBookingDTO createBooking(ActivityBookingDTO bookingDTO);

    List<ActivityBookingDTO> getAllBookings();

    ActivityBookingDTO getBookingById(Long id);

    List<ActivityBookingDTO> getBookingsByUserId(Long userId);

    void deleteBooking(Long id);
}
