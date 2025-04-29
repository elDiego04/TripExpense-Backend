package com.tripexpense.service.impl;

import com.tripexpense.dto.ActivityDTO;
import com.tripexpense.entity.Activity;
import com.tripexpense.entity.City;
import com.tripexpense.repository.ActivityRepository;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.service.interfac.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        if (activityRepository.existsByNameAndCityCityId(activityDTO.getName(), activityDTO.getCityId())) {
            throw new RuntimeException("Activity already exists in this city");
        }

        City city = cityRepository.findById(activityDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Activity activity = new Activity();
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());
        activity.setCity(city);
        activity.setCategory(activityDTO.getCategory());
        activity.setDuration(activityDTO.getDuration());
        activity.setPrice(activityDTO.getPrice());
        activity.setLocation(activityDTO.getLocation());
        activity.setAvailabilityDates(new ArrayList<>(activityDTO.getAvailabilityDates()));
        activity.setMinPeople(activityDTO.getMinPeople());
        activity.setMaxPeople(activityDTO.getMaxPeople());
        activity.setDifficultyLevel(activityDTO.getDifficultyLevel());

        Activity savedActivity = activityRepository.save(activity);
        return convertToDTO(savedActivity);
    }

    @Override
    public ActivityDTO getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        return convertToDTO(activity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityDTO> getActivitiesByCity(Long cityId) {
        return activityRepository.findByCityCityId(cityId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityDTO> searchActivities(String query) {
        return activityRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        if (activityDTO.getName() != null) existingActivity.setName(activityDTO.getName());
        if (activityDTO.getDescription() != null) existingActivity.setDescription(activityDTO.getDescription());
        if (activityDTO.getCategory() != null) existingActivity.setCategory(activityDTO.getCategory());
        if (activityDTO.getDuration() != null) existingActivity.setDuration(activityDTO.getDuration());
        if (activityDTO.getPrice() != null) existingActivity.setPrice(activityDTO.getPrice());
        if (activityDTO.getLocation() != null) existingActivity.setLocation(activityDTO.getLocation());
        if (activityDTO.getAvailabilityDates() != null)
            existingActivity.setAvailabilityDates(new ArrayList<>(activityDTO.getAvailabilityDates()));
        if (activityDTO.getMinPeople() != null) existingActivity.setMinPeople(activityDTO.getMinPeople());
        if (activityDTO.getMaxPeople() != null) existingActivity.setMaxPeople(activityDTO.getMaxPeople());
        if (activityDTO.getDifficultyLevel() != null) existingActivity.setDifficultyLevel(activityDTO.getDifficultyLevel());

        if (activityDTO.getCityId() != null && !activityDTO.getCityId().equals(existingActivity.getCity().getCityId())) {
            City newCity = cityRepository.findById(activityDTO.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            existingActivity.setCity(newCity);
        }

        Activity updatedActivity = activityRepository.save(existingActivity);
        return convertToDTO(updatedActivity);
    }

    @Override
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new RuntimeException("Activity not found with id: " + id);
        }
        activityRepository.deleteById(id);
    }

    @Override
    public List<ActivityDTO> findActivitiesByCategory(String category) {
        return activityRepository.findByCategoryIgnoreCase(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ActivityDTO convertToDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(activity.getActivityId());
        dto.setName(activity.getName());
        dto.setDescription(activity.getDescription());
        dto.setCityId(activity.getCity().getCityId());
        dto.setCityName(activity.getCity().getName());
        dto.setCategory(activity.getCategory());
        dto.setDuration(activity.getDuration());
        dto.setPrice(activity.getPrice());
        dto.setLocation(activity.getLocation());
        dto.setAvailabilityDates(new ArrayList<>(activity.getAvailabilityDates()));
        dto.setMinPeople(activity.getMinPeople());
        dto.setMaxPeople(activity.getMaxPeople());
        dto.setDifficultyLevel(activity.getDifficultyLevel());
        return dto;
    }
}
