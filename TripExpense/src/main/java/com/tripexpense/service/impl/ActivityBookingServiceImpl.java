package com.tripexpense.service.impl;

import com.tripexpense.dto.ActivityBookingDTO;
import com.tripexpense.entity.Activity;
import com.tripexpense.entity.ActivityBooking;
import com.tripexpense.entity.User;
import com.tripexpense.exception.ResourceNotFoundException;
import com.tripexpense.repository.ActivityBookingRepository;
import com.tripexpense.repository.ActivityRepository;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.interfac.ActivityBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityBookingServiceImpl implements ActivityBookingService {

    private final ActivityBookingRepository bookingRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActivityBookingServiceImpl(ActivityBookingRepository bookingRepository,
                                      ActivityRepository activityRepository,
                                      UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ActivityBookingDTO createBooking(@Valid ActivityBookingDTO bookingDTO) {
        Activity activity = activityRepository.findById(bookingDTO.getActivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada con ID: " + bookingDTO.getActivityId()));
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + bookingDTO.getUserId()));

        ActivityBooking booking = new ActivityBooking();
        booking.setActivity(activity);
        booking.setUser(user);
        booking.setNumberOfPeople(bookingDTO.getNumberOfPeople());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setTotalPrice(bookingDTO.getTotalPrice());

        ActivityBooking saved = bookingRepository.save(booking);
        return convertToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityBookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityBookingDTO getBookingById(Long id) {
        ActivityBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
        return convertToDTO(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityBookingDTO> getBookingsByUserId(Long userId) {
        List<ActivityBooking> bookings = bookingRepository.findByUser_Id(userId);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reserva no encontrada con ID: " + id);
        }
        bookingRepository.deleteById(id);
    }

    private ActivityBookingDTO convertToDTO(ActivityBooking booking) {
        ActivityBookingDTO dto = new ActivityBookingDTO();
        dto.setActivityBookingId(booking.getActivityBookingId());
        dto.setActivityId(booking.getActivity().getActivityId());
        dto.setUserId(booking.getUser().getId());
        dto.setNumberOfPeople(booking.getNumberOfPeople());
        dto.setBookingDate(booking.getBookingDate());
        dto.setTotalPrice(booking.getTotalPrice());
        return dto;
    }
}
