package com.tripexpense.repository;

import com.tripexpense.dto.UserDTO;
import com.tripexpense.entity.TravelPlan;
import com.tripexpense.enums.PlanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    List<TravelPlan> findByUser(UserDTO userDTO);
    List<TravelPlan> findByDepartureDate(LocalDate departureDate);
    List<TravelPlan> findByDestinationCity_Name(String cityName);
    List<TravelPlan> findByStatus(PlanStatus travelPlanStatus);

    List<TravelPlan> findByUserId(Long userId);
}
