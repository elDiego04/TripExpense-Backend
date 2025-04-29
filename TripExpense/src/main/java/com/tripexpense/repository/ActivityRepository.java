package com.tripexpense.repository;

import com.tripexpense.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
    List<Activity> findByCityCityId(Long cityId);

    List<Activity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query, String query1);

    List<Activity> findByCategoryIgnoreCase(String category);

    boolean existsByNameAndCityCityId(String name, Long cityId);
}
