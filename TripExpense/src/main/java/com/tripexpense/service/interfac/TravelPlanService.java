package com.tripexpense.service.interfac;

import com.tripexpense.dto.TravelPlanDTO;

import java.util.List;

public interface TravelPlanService {
    TravelPlanDTO createTravelPlan(TravelPlanDTO travelPlanDTO);
    TravelPlanDTO getTravelPlanById(Long id);
    List<TravelPlanDTO> getAllTravelPlans();
    List<TravelPlanDTO> getTravelPlansByUser(Long userId);
    TravelPlanDTO updateTravelPlan(Long id, TravelPlanDTO travelPlanDTO);
    void deleteTravelPlan(Long id);
    TravelPlanDTO addActivityToPlan(Long planId, Long activityId);
    TravelPlanDTO removeActivityFromPlan(Long planId, Long activityId);
}
