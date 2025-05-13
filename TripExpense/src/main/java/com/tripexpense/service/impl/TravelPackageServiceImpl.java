package com.tripexpense.service.impl;

import com.tripexpense.dto.TravelPackageDTO;
import com.tripexpense.entity.*;
import com.tripexpense.enums.PackageType;
import com.tripexpense.exception.DuplicateResourceException;
import com.tripexpense.repository.*;
import com.tripexpense.service.interfac.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;
    private final CityRepository cityRepository;
    private final FlightRepository flightRepository;
    private final HotelRepository hotelRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository,
                                    CityRepository cityRepository,
                                    FlightRepository flightRepository,
                                    HotelRepository hotelRepository,
                                    ActivityRepository activityRepository) {
        this.travelPackageRepository = travelPackageRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
        this.hotelRepository = hotelRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    @Transactional
    public TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO) {
        if (travelPackageRepository.existsByNameAndDestinationCityId(
                travelPackageDTO.getName(),
                travelPackageDTO.getDestination().getCityId())) {
            throw new DuplicateResourceException("Ya existe un paquete con este nombre para este destino");
        }

        TravelPackage travelPackage = new TravelPackage();
        mapDTOToEntity(travelPackageDTO, travelPackage);

        TravelPackage savedPackage = travelPackageRepository.save(travelPackage);
        return convertToDTO(savedPackage);
    }

    @Override
    @Transactional(readOnly = true)
    public TravelPackageDTO getTravelPackageById(Long id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paquete no encontrado con ID: " + id));
        return convertToDTO(travelPackage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPackageDTO> getAllTravelPackages() {
        return travelPackageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPackageDTO> getTravelPackagesByDestination(Long cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new NoSuchElementException("Ciudad no encontrada con ID: " + cityId);
        }
        return travelPackageRepository.findByDestinationCityId(cityId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPackageDTO> getTravelPackagesByType(PackageType type) {
        return travelPackageRepository.findByType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPackageDTO> searchTravelPackages(String query) {
        return travelPackageRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO) {
        TravelPackage existingPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Paquete no encontrado con ID: " + id));

        if (!existingPackage.getName().equals(travelPackageDTO.getName())) {
            if (travelPackageRepository.existsByNameAndDestinationCityId(
                    travelPackageDTO.getName(),
                    existingPackage.getDestination().getCityId())) {
                throw new DuplicateResourceException("Ya existe un paquete con este nombre para este destino");
            }
        }

        mapDTOToEntity(travelPackageDTO, existingPackage);
        TravelPackage updatedPackage = travelPackageRepository.save(existingPackage);
        return convertToDTO(updatedPackage);
    }

    @Override
    @Transactional
    public void deleteTravelPackage(Long id) {
        if (!travelPackageRepository.existsById(id)) {
            throw new NoSuchElementException("Paquete no encontrado con ID: " + id);
        }
        travelPackageRepository.deleteById(id);
    }

    private void mapDTOToEntity(TravelPackageDTO dto, TravelPackage entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl());
        entity.setBasePrice(dto.getBasePrice());
        entity.setDurationDays(dto.getDurationDays());
        entity.setType(dto.getType());

        City destination = cityRepository.findById(dto.getDestination().getCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad destino no encontrada"));
        entity.setDestination(destination);

        if (dto.getIncludedFlight() != null) {
            Flight flight = flightRepository.findById(dto.getIncludedFlight().getFlightId())
                    .orElseThrow(() -> new NoSuchElementException("Vuelo no encontrado"));
            entity.setIncludedFlight(flight);
        }

        if (dto.getIncludedHotel() != null) {
            Hotel hotel = hotelRepository.findById(dto.getIncludedHotel().getHotelId())
                    .orElseThrow(() -> new NoSuchElementException("Hotel no encontrado"));
            entity.setIncludedHotel(hotel);
        }

        if (dto.getIncludedActivities() != null && !dto.getIncludedActivities().isEmpty()) {
            List<Long> activityIds = dto.getIncludedActivities().stream()
                    .map(Activity::getActivityId)
                    .collect(Collectors.toList());
            List<Activity> activities = activityRepository.findAllById(activityIds);
            if (activities.size() != activityIds.size()) {
                throw new NoSuchElementException("Una o más actividades no fueron encontradas");
            }
            entity.setIncludedActivities(activities);
        }
    }

    private TravelPackageDTO convertToDTO(TravelPackage travelPackage) {
        TravelPackageDTO dto = new TravelPackageDTO();
        dto.setTravelPackageId(travelPackage.getTravelPackageId());
        dto.setName(travelPackage.getName());
        dto.setDescription(travelPackage.getDescription());
        dto.setImageUrl(travelPackage.getImageUrl());
        dto.setDestination(travelPackage.getDestination());
        dto.setBasePrice(travelPackage.getBasePrice());
        dto.setDurationDays(travelPackage.getDurationDays());
        dto.setIncludedFlight(travelPackage.getIncludedFlight());
        dto.setIncludedHotel(travelPackage.getIncludedHotel());
        dto.setIncludedActivities(travelPackage.getIncludedActivities());
        dto.setType(travelPackage.getType());
        return dto;
    }
}