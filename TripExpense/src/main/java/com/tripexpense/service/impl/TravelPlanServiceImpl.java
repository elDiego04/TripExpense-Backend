package com.tripexpense.service.impl;

import com.tripexpense.dto.TravelPlanDTO;
import com.tripexpense.entity.*;
import com.tripexpense.enums.PlanStatus;
import com.tripexpense.repository.*;
import com.tripexpense.service.interfac.TravelPlanService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public TravelPlanDTO createTravelPlan(TravelPlanDTO travelPlanDTO) {
        travelPlanDTO.validateDates();

        User user = userRepository.findById(travelPlanDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setUser(user);
        travelPlan.setTitle(travelPlanDTO.getTitle());
        travelPlan.setDescription(travelPlanDTO.getDescription());
        travelPlan.setStartDate(travelPlanDTO.getStartDate());
        travelPlan.setEndDate(travelPlanDTO.getEndDate());
        travelPlan.setAdults(travelPlanDTO.getAdults());
        travelPlan.setChildren(travelPlanDTO.getChildren());
        travelPlan.setStatus(PlanStatus.PLANNING);
        travelPlan.setTotalCost(travelPlanDTO.getTotalCost());
        travelPlan.setNotes(travelPlanDTO.getNotes());

        if (travelPlanDTO.getDestinationCityId() != null) {
            City destination = cityRepository.findById(travelPlanDTO.getDestinationCityId())
                    .orElseThrow(() -> new NoSuchElementException("Ciudad destino no encontrada"));
            travelPlan.setDestination(destination);
        }

        if (travelPlanDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(travelPlanDTO.getFlightId())
                    .orElseThrow(() -> new NoSuchElementException("Vuelo no encontrado"));
            travelPlan.setFlight(flight);
        }

        if (travelPlanDTO.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(travelPlanDTO.getHotelId())
                    .orElseThrow(() -> new NoSuchElementException("Hotel no encontrado"));
            travelPlan.setHotel(hotel);
        }

        TravelPlan savedPlan = travelPlanRepository.save(travelPlan);

        if (travelPlanDTO.getActivityIds() != null && !travelPlanDTO.getActivityIds().isEmpty()) {
            List<Activity> activities = activityRepository.findAllById(travelPlanDTO.getActivityIds());
            if (activities.size() != travelPlanDTO.getActivityIds().size()) {
                throw new NoSuchElementException("Algunas actividades no fueron encontradas");
            }
            savedPlan.setActivities(activities);
            savedPlan = travelPlanRepository.save(savedPlan);
        }

        return convertToFullDTO(savedPlan);
    }

    @Override
    @Transactional(readOnly = true)
    public TravelPlanDTO getTravelPlanById(Long id) {
        TravelPlan travelPlan = travelPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plan de viaje no encontrado con ID: " + id));
        return convertToFullDTO(travelPlan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPlanDTO> getAllTravelPlans() {
        return travelPlanRepository.findAll().stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly=true)
    public List<TravelPlanDTO> getTravelPlansByUser(Long userId) {
        return travelPlanRepository.findByUserId(userId).stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TravelPlanDTO updateTravelPlan(Long id, TravelPlanDTO travelPlanDTO) {
        if (travelPlanDTO.getEndDate() != null && travelPlanDTO.getStartDate() != null) {
            travelPlanDTO.validateDates();
        }

        TravelPlan existingPlan = travelPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plan de viaje no encontrado con ID: " + id));

        if (travelPlanDTO.getTitle() != null) existingPlan.setTitle(travelPlanDTO.getTitle());
        if (travelPlanDTO.getDescription() != null) existingPlan.setDescription(travelPlanDTO.getDescription());
        if (travelPlanDTO.getStartDate() != null) existingPlan.setStartDate(travelPlanDTO.getStartDate());
        if (travelPlanDTO.getEndDate() != null) existingPlan.setEndDate(travelPlanDTO.getEndDate());
        if (travelPlanDTO.getAdults() != null) existingPlan.setAdults(travelPlanDTO.getAdults());
        if (travelPlanDTO.getChildren() != null) existingPlan.setChildren(travelPlanDTO.getChildren());
        if (travelPlanDTO.getStatus() != null) existingPlan.setStatus(travelPlanDTO.getStatus());
        if (travelPlanDTO.getTotalCost() != null) existingPlan.setTotalCost(travelPlanDTO.getTotalCost());
        if (travelPlanDTO.getNotes() != null) existingPlan.setNotes(travelPlanDTO.getNotes());

        if (travelPlanDTO.getDestinationCityId() != null) {
            City destination = cityRepository.findById(travelPlanDTO.getDestinationCityId())
                    .orElseThrow(() -> new NoSuchElementException("Ciudad destino no encontrada"));
            existingPlan.setDestination(destination);
        }

        if (travelPlanDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(travelPlanDTO.getFlightId())
                    .orElseThrow(() -> new NoSuchElementException("Vuelo no encontrado"));
            existingPlan.setFlight(flight);
        }

        if (travelPlanDTO.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(travelPlanDTO.getHotelId())
                    .orElseThrow(() -> new NoSuchElementException("Hotel no encontrado"));
            existingPlan.setHotel(hotel);
        }

        if (travelPlanDTO.getActivityIds() != null) {
            List<Activity> activities = activityRepository.findAllById(travelPlanDTO.getActivityIds());
            if (activities.size() != travelPlanDTO.getActivityIds().size()) {
                throw new NoSuchElementException("Algunas actividades no fueron encontradas");
            }
            existingPlan.setActivities(activities);
        }

        TravelPlan updatedPlan = travelPlanRepository.save(existingPlan);
        return convertToFullDTO(updatedPlan);
    }

    @Override
    @Transactional
    public void deleteTravelPlan(Long id) {
        if (!travelPlanRepository.existsById(id)) {
            throw new NoSuchElementException("Plan de viaje no encontrado con ID: " + id);
        }
        travelPlanRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TravelPlanDTO addActivityToPlan(Long planId, Long activityId) {
        TravelPlan travelPlan = travelPlanRepository.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Plan de viaje no encontrado"));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new NoSuchElementException("Actividad no encontrada"));

        if (travelPlan.getActivities() == null) {
            travelPlan.setActivities(new ArrayList<>());
        }

        if (!travelPlan.getActivities().contains(activity)) {
            travelPlan.getActivities().add(activity);
        }

        TravelPlan updatedPlan = travelPlanRepository.save(travelPlan);
        return convertToFullDTO(updatedPlan);
    }

    @Override
    @Transactional
    public TravelPlanDTO removeActivityFromPlan(Long planId, Long activityId) {
        TravelPlan travelPlan = travelPlanRepository.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Plan de viaje no encontrado"));

        if (travelPlan.getActivities() != null) {
            travelPlan.getActivities().removeIf(a -> a.getActivityId().equals(activityId));
        }

        TravelPlan updatedPlan = travelPlanRepository.save(travelPlan);
        return convertToFullDTO(updatedPlan);
    }

    private TravelPlanDTO convertToFullDTO(TravelPlan travelPlan) {
        TravelPlanDTO dto = new TravelPlanDTO();

        dto.setUser(travelPlan.getUser());
        dto.setDestination(travelPlan.getDestination());
        dto.setFlight(travelPlan.getFlight());
        dto.setHotel(travelPlan.getHotel());
        dto.setActivities(travelPlan.getActivities());

        dto.setTravelPlanId(travelPlan.getTravelPlanId());
        dto.setUserId(travelPlan.getUser().getId());
        if (travelPlan.getDestination() != null) {
            dto.setDestinationCityId(travelPlan.getDestination().getCityId());
        }
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

        dto.setTitle(travelPlan.getTitle());
        dto.setDescription(travelPlan.getDescription());
        dto.setStartDate(travelPlan.getStartDate());
        dto.setEndDate(travelPlan.getEndDate());
        dto.setAdults(travelPlan.getAdults());
        dto.setChildren(travelPlan.getChildren());
        dto.setTotalCost(travelPlan.getTotalCost());
        dto.setStatus(travelPlan.getStatus());
        dto.setNotes(travelPlan.getNotes());

        return dto;
    }
}
