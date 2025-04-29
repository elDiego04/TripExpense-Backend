package com.tripexpense.service.impl;

import com.tripexpense.dto.TravelPlanDTO;
import com.tripexpense.entity.*;
import com.tripexpense.enums.PlanStatus;
import com.tripexpense.repository.*;
import com.tripexpense.service.interfac.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public TravelPlanDTO createTravelPlan(TravelPlanDTO travelPlanDTO) {
        User user = userRepository.findById(travelPlanDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setUser(user);
        travelPlan.setTitle(travelPlanDTO.getTitle());
        travelPlan.setDescription(travelPlanDTO.getDescription());
        travelPlan.setDepartureDate(travelPlanDTO.getDepartureDate());
        travelPlan.setReturnDate(travelPlanDTO.getReturnDate());
        travelPlan.setAdults(travelPlanDTO.getAdults());
        travelPlan.setChildren(travelPlanDTO.getChildren());
        travelPlan.setStatus(PlanStatus.PLANNING);


        if (travelPlanDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(travelPlanDTO.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found"));
            travelPlan.setFlight(flight);
        }


        if (travelPlanDTO.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(travelPlanDTO.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            travelPlan.setHotel(hotel);
        }

        TravelPlan savedPlan = travelPlanRepository.save(travelPlan);

        if (travelPlanDTO.getActivityIds() != null && !travelPlanDTO.getActivityIds().isEmpty()) {
            Set<Activity> activities = new HashSet<>(activityRepository.findAllById(travelPlanDTO.getActivityIds()));
            savedPlan.setActivities(new ArrayList<>(activities));
            savedPlan = travelPlanRepository.save(savedPlan);
        }

        return convertToDTO(savedPlan);
    }

    @Override
    public TravelPlanDTO getTravelPlanById(Long id) {
        TravelPlan travelPlan = travelPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel plan not found with id: " + id));
        return convertToDTO(travelPlan);
    }

    @Override
    public List<TravelPlanDTO> getAllTravelPlans() {
        return travelPlanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelPlanDTO> getTravelPlansByUser(Long userId) {
        return travelPlanRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TravelPlanDTO updateTravelPlan(Long id, TravelPlanDTO travelPlanDTO) {
        TravelPlan existingPlan = travelPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel plan not found with id: " + id));

        if (travelPlanDTO.getTitle() != null) existingPlan.setTitle(travelPlanDTO.getTitle());
        if (travelPlanDTO.getDescription() != null) existingPlan.setDescription(travelPlanDTO.getDescription());
        if (travelPlanDTO.getDepartureDate() != null) existingPlan.setDepartureDate(travelPlanDTO.getDepartureDate());
        if (travelPlanDTO.getReturnDate() != null) existingPlan.setReturnDate(travelPlanDTO.getReturnDate());
        if (travelPlanDTO.getAdults() != null) existingPlan.setAdults(travelPlanDTO.getAdults());
        if (travelPlanDTO.getChildren() != null) existingPlan.setChildren(travelPlanDTO.getChildren());
        if (travelPlanDTO.getStatus() != null) existingPlan.setStatus(travelPlanDTO.getStatus());

        if (travelPlanDTO.getFlightId() != null &&
                (existingPlan.getFlight() == null || !travelPlanDTO.getFlightId().equals(existingPlan.getFlight().getFlightId()))) {
            Flight flight = flightRepository.findById(travelPlanDTO.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found"));
            existingPlan.setFlight(flight);
        }

        if (travelPlanDTO.getHotelId() != null &&
                (existingPlan.getHotel() == null || !travelPlanDTO.getHotelId().equals(existingPlan.getHotel().getHotelId()))) {
            Hotel hotel = hotelRepository.findById(travelPlanDTO.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            existingPlan.setHotel(hotel);
        }

        TravelPlan updatedPlan = travelPlanRepository.save(existingPlan);
        return convertToDTO(updatedPlan);
    }

    @Override
    public void deleteTravelPlan(Long id) {
        if (!travelPlanRepository.existsById(id)) {
            throw new RuntimeException("Travel plan not found with id: " + id);
        }
        travelPlanRepository.deleteById(id);
    }

    @Override
    public TravelPlanDTO addActivityToPlan(Long planId, Long activityId) {
        TravelPlan travelPlan = travelPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Travel plan not found"));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        if (travelPlan.getActivities() == null) {
            travelPlan.setActivities(new ArrayList<>());
        }

        travelPlan.getActivities().add(activity);
        TravelPlan updatedPlan = travelPlanRepository.save(travelPlan);
        return convertToDTO(updatedPlan);
    }

    @Override
    public TravelPlanDTO removeActivityFromPlan(Long planId, Long activityId) {
        TravelPlan travelPlan = travelPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Travel plan not found"));

        if (travelPlan.getActivities() != null) {
            travelPlan.getActivities().removeIf(a -> a.getActivityId().equals(activityId));
        }

        TravelPlan updatedPlan = travelPlanRepository.save(travelPlan);
        return convertToDTO(updatedPlan);
    }

    private TravelPlanDTO convertToDTO(TravelPlan travelPlan) {
        TravelPlanDTO dto = new TravelPlanDTO();
        dto.setId(travelPlan.getTravelPlanId());
        dto.setUserId(travelPlan.getUser().getId());
        dto.setTitle(travelPlan.getTitle());
        dto.setDescription(travelPlan.getDescription());
        dto.setDepartureDate(travelPlan.getDepartureDate());
        dto.setReturnDate(travelPlan.getReturnDate());
        dto.setAdults(travelPlan.getAdults());
        dto.setChildren(travelPlan.getChildren());
        dto.setStatus(travelPlan.getStatus());
        dto.setCreatedAt(travelPlan.getCreatedAt());
        dto.setUpdatedAt(travelPlan.getUpdatedAt());

        if (travelPlan.getFlight() != null) {
            dto.setFlightId(travelPlan.getFlight().getFlightId());
        }

        if (travelPlan.getHotel() != null) {
            dto.setHotelId(travelPlan.getHotel().getHotelId());
        }

        if (travelPlan.getActivities() != null) {
            dto.setActivityIds(travelPlan.getActivities().stream()
                    .map(Activity::getActivityId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
