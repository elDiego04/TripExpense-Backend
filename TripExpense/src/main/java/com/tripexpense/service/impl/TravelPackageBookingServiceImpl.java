package com.tripexpense.service.impl;

import com.tripexpense.dto.TravelPackageBookingDTO;
import com.tripexpense.entity.TravelPackage;
import com.tripexpense.entity.TravelPackageBooking;
import com.tripexpense.entity.User;
import com.tripexpense.repository.TravelPackageBookingRepository;
import com.tripexpense.repository.TravelPackageRepository;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.TravelPackageBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelPackageBookingServiceImpl implements TravelPackageBookingService {

    @Autowired
    private TravelPackageBookingRepository bookingRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private UserRepository userRepository;

    private TravelPackageBookingDTO convertToDTO(TravelPackageBooking booking) {
        TravelPackageBookingDTO dto = new TravelPackageBookingDTO();
        dto.setId(booking.getId());
        dto.setTravelPackageId(booking.getTravelPackage().getId());
        dto.setUserId(booking.getUser().getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setNumberOfAdults(booking.getNumberOfAdults());
        dto.setNumberOfChildren(booking.getNumberOfChildren());
        dto.setTotalPrice(booking.getTotalPrice());
        return dto;
    }

    @Override
    public TravelPackageBookingDTO createBooking(TravelPackageBookingDTO dto) {
        TravelPackage travelPackage = travelPackageRepository.findById(dto.getTravelPackageId())
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        TravelPackageBooking booking = new TravelPackageBooking();
        booking.setTravelPackage(travelPackage);
        booking.setUser(user);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setNumberOfAdults(dto.getNumberOfAdults());
        booking.setNumberOfChildren(dto.getNumberOfChildren());
        booking.setTotalPrice(dto.getTotalPrice());

        TravelPackageBooking saved = bookingRepository.save(booking);
        return convertToDTO(saved);
    }

    @Override
    public List<TravelPackageBookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TravelPackageBookingDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<TravelPackageBookingDTO> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TravelPackageBookingDTO updateBooking(Long id, TravelPackageBookingDTO dto) {
        Optional<TravelPackageBooking> optional = bookingRepository.findById(id);
        if (optional.isEmpty()) return null;

        TravelPackageBooking booking = optional.get();

        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setNumberOfAdults(dto.getNumberOfAdults());
        booking.setNumberOfChildren(dto.getNumberOfChildren());
        booking.setTotalPrice(dto.getTotalPrice());

        TravelPackageBooking updated = bookingRepository.save(booking);
        return convertToDTO(updated);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
