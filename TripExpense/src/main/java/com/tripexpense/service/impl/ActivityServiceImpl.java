package com.tripexpense.service.impl;

import com.tripexpense.dto.ActivityDTO;
import com.tripexpense.entity.Activity;
import com.tripexpense.entity.City;
import com.tripexpense.exception.DuplicateResourceException;
import com.tripexpense.exception.ResourceNotFoundException;
import com.tripexpense.repository.ActivityRepository;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.service.interfac.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final CityRepository cityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, CityRepository cityRepository) {
        this.activityRepository = activityRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        if (activityRepository.existsByNameAndCityCityId(activityDTO.getName(), activityDTO.getCityId())) {
            throw new DuplicateResourceException("Ya existe una actividad con este nombre en esta ciudad");
        }

        City city = cityRepository.findById(activityDTO.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada con ID: " + activityDTO.getCityId()));

        Activity activity = new Activity();
        mapDTOToEntity(activityDTO, activity);
        activity.setCity(city);

        Activity savedActivity = activityRepository.save(activity);
        return convertToDTO(savedActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityDTO getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada con ID: " + id));
        return convertToDTO(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityDTO> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityDTO> getActivitiesByCity(Long cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("Ciudad no encontrada con ID: " + cityId);
        }
        return activityRepository.findByCityCityId(cityId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<ActivityDTO> searchActivities(String query) {
        return activityRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada con ID: " + id));

        if (!existingActivity.getName().equals(activityDTO.getName()) &&
                activityRepository.existsByNameAndCityCityId(activityDTO.getName(), activityDTO.getCityId())) {
            throw new DuplicateResourceException("Ya existe una actividad con este nombre en esta ciudad");
        }

        if (!existingActivity.getCity().getCityId().equals(activityDTO.getCityId())) {
            City newCity = cityRepository.findById(activityDTO.getCityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada con ID: " + activityDTO.getCityId()));
            existingActivity.setCity(newCity);
        }

        mapDTOToEntity(activityDTO, existingActivity);

        Activity updatedActivity = activityRepository.save(existingActivity);
        return convertToDTO(updatedActivity);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Actividad no encontrada con ID: " + id);
        }
        activityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityDTO> findActivitiesByCategory(String category) {
        return activityRepository.findByCategoryIgnoreCase(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void mapDTOToEntity(ActivityDTO dto, Activity entity) {
        entity.setName(dto.getName());
        entity.setImageUrl(dto.getImageUrl());
        entity.setCategory(dto.getCategory());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        entity.setLocation(dto.getLocation());
        entity.setDifficultyLevel(dto.getDifficultyLevel());
    }

    private ActivityDTO convertToDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(activity.getActivityId());
        dto.setCityId(activity.getCity().getCityId());
        dto.setCityName(activity.getCity().getName());
        dto.setName(activity.getName());
        dto.setImageUrl(activity.getImageUrl());
        dto.setCategory(activity.getCategory());
        dto.setDuration(activity.getDuration());
        dto.setPrice(activity.getPrice());
        dto.setLocation(activity.getLocation());
        dto.setDifficultyLevel(activity.getDifficultyLevel());
        return dto;
    }
}
