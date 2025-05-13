package com.tripexpense.repository;

import com.tripexpense.entity.TravelPackage;
import com.tripexpense.enums.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long>, JpaSpecificationExecutor<TravelPackage> {
    List<TravelPackage> findByDestinationCityId(Long cityId);
    List<TravelPackage> findByType(PackageType type);
    List<TravelPackage> findByNameContainingIgnoreCase(String name);
    boolean existsByNameAndDestinationCityId(String name, Long cityId);
}