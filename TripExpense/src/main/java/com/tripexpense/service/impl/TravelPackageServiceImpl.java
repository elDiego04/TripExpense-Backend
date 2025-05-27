package com.tripexpense.service.impl;

import com.tripexpense.dto.TravelPackageDTO;
import com.tripexpense.entity.Activity;
import com.tripexpense.entity.TravelPackage;
import com.tripexpense.repository.*;
import com.tripexpense.service.interfac.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired private TravelPackageRepository travelPackageRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private HotelRepository hotelRepository;
    @Autowired private ActivityRepository activityRepository;

    private TravelPackageDTO convertToDTO(TravelPackage travelPackage) {
        TravelPackageDTO dto = new TravelPackageDTO();
        dto.setTravelPackageId(travelPackage.getId());
        dto.setName(travelPackage.getName());
        dto.setDescription(travelPackage.getDescription());
        dto.setImageUrl(travelPackage.getImageUrl());
        dto.setDestinationCityId(travelPackage.getDestination().getCityId());
        dto.setBasePrice(travelPackage.getBasePrice());
        dto.setDurationDays(travelPackage.getDurationDays());
        dto.setIncludedFlightId(
                travelPackage.getIncludedFlight() != null ? travelPackage.getIncludedFlight().getFlightId() : null);
        dto.setIncludedHotelId(
                travelPackage.getIncludedHotel() != null ? travelPackage.getIncludedHotel().getHotelId() : null);
        dto.setIncludedActivityIds(
                travelPackage.getIncludedActivities() != null
                        ? travelPackage.getIncludedActivities().stream().map(Activity::getActivityId).collect(Collectors.toList())
                        : null);
        dto.setType(travelPackage.getType());
        return dto;
    }

    private TravelPackage convertToEntity(TravelPackageDTO dto) {
        TravelPackage entity = new TravelPackage();
        entity.setId(dto.getTravelPackageId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl());
        entity.setDestination(cityRepository.findById(dto.getDestinationCityId()).orElse(null));
        entity.setBasePrice(dto.getBasePrice());
        entity.setDurationDays(dto.getDurationDays());
        if (dto.getIncludedFlightId() != null) {
            entity.setIncludedFlight(flightRepository.findById(dto.getIncludedFlightId()).orElse(null));
        }
        if (dto.getIncludedHotelId() != null) {
            entity.setIncludedHotel(hotelRepository.findById(dto.getIncludedHotelId()).orElse(null));
        }
        if (dto.getIncludedActivityIds() != null) {
            List<Activity> activities = activityRepository.findAllById(dto.getIncludedActivityIds());
            entity.setIncludedActivities(activities);
        }
        entity.setType(dto.getType());
        return entity;
    }

    @Override
    public TravelPackageDTO createTravelPackage(TravelPackageDTO dto) {
        TravelPackage saved = travelPackageRepository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    @Override
    public List<TravelPackageDTO> getAllTravelPackages() {
        return travelPackageRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TravelPackageDTO getTravelPackageById(Long id) {
        return travelPackageRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO dto) {
        Optional<TravelPackage> existing = travelPackageRepository.findById(id);
        if (existing.isPresent()) {
            TravelPackage updated = convertToEntity(dto);
            updated.setId(id);
            return convertToDTO(travelPackageRepository.save(updated));
        }
        return null;
    }

    @Override
    public void deleteTravelPackage(Long id) {
        travelPackageRepository.deleteById(id);
    }
}
