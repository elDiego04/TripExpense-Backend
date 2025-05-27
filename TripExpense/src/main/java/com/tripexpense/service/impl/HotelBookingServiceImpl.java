package com.tripexpense.service.impl;

import com.tripexpense.dto.HotelBookingDTO;
import com.tripexpense.entity.Hotel;
import com.tripexpense.entity.HotelBooking;
import com.tripexpense.entity.User;
import com.tripexpense.repository.HotelBookingRepository;
import com.tripexpense.repository.HotelRepository;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.interfac.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    private HotelBookingDTO convertToDTO(HotelBooking booking) {
        HotelBookingDTO dto = new HotelBookingDTO();
        dto.setId(booking.getId());
        dto.setHotelId(booking.getHotel().getHotelId());
        dto.setUserId(booking.getUser().getId());
        dto.setRoomTypes(booking.getRoomTypes());
        dto.setPricePerNight(booking.getPricePerNight());
        dto.setMaxAdults(booking.getMaxAdults());
        dto.setMaxChildren(booking.getMaxChildren());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        return dto;
    }

    private HotelBooking convertToEntity(HotelBookingDTO dto) {
        HotelBooking booking = new HotelBooking();
        booking.setId(dto.getId());
        booking.setRoomTypes(dto.getRoomTypes());
        booking.setPricePerNight(dto.getPricePerNight());
        booking.setMaxAdults(dto.getMaxAdults());
        booking.setMaxChildren(dto.getMaxChildren());
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());

        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        Optional<User> user = userRepository.findById(dto.getUserId());

        hotel.ifPresent(booking::setHotel);
        user.ifPresent(booking::setUser);

        return booking;
    }

    @Override
    public HotelBookingDTO createHotelBooking(HotelBookingDTO dto) {
        HotelBooking booking = convertToEntity(dto);
        return convertToDTO(hotelBookingRepository.save(booking));
    }

    @Override
    public List<HotelBookingDTO> getAllHotelBookings() {
        return hotelBookingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelBookingDTO getHotelBookingById(Long id) {
        return hotelBookingRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public HotelBookingDTO updateHotelBooking(Long id, HotelBookingDTO dto) {
        Optional<HotelBooking> existing = hotelBookingRepository.findById(id);
        if (existing.isPresent()) {
            HotelBooking booking = convertToEntity(dto);
            booking.setId(id);
            return convertToDTO(hotelBookingRepository.save(booking));
        }
        return null;
    }

    @Override
    public void deleteHotelBooking(Long id) {
        hotelBookingRepository.deleteById(id);
    }

    @Override
    public List<HotelBookingDTO> getHotelBookingsByUserId(Long userId) {
        return hotelBookingRepository.findByUser_Id(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
