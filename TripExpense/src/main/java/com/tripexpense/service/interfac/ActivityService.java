package com.tripexpense.service.interfac;

import com.tripexpense.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityDTO getActivityById(Long id);
    List<ActivityDTO> getAllActivities();
    List<ActivityDTO> getActivitiesByCity(Long cityId);
    List<ActivityDTO> searchActivities(String query);
    ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);
    void deleteActivity(Long id);
    List<ActivityDTO> findActivitiesByCategory(String category);
}