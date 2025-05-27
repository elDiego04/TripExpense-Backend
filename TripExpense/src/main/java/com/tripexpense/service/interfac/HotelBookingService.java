package com.tripexpense.service.interfac;

import com.tripexpense.dto.HotelBookingDTO;

import java.util.List;

public interface HotelBookingService {
    HotelBookingDTO createHotelBooking(HotelBookingDTO hotelBookingDTO);
    List<HotelBookingDTO> getAllHotelBookings();
    HotelBookingDTO getHotelBookingById(Long id);
    HotelBookingDTO updateHotelBooking(Long id, HotelBookingDTO hotelBookingDTO);
    void deleteHotelBooking(Long id);
    List<HotelBookingDTO> getHotelBookingsByUserId(Long userId);
}
