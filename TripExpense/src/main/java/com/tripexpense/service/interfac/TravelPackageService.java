package com.tripexpense.service.interfac;

import com.tripexpense.dto.TravelPackageDTO;
import com.tripexpense.enums.PackageType;

import java.util.List;

public interface TravelPackageService {
    TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO);
    TravelPackageDTO getTravelPackageById(Long id);
    List<TravelPackageDTO> getAllTravelPackages();
    List<TravelPackageDTO> getTravelPackagesByDestination(Long cityId);
    List<TravelPackageDTO> getTravelPackagesByType(PackageType type);
    List<TravelPackageDTO> searchTravelPackages(String query);
    TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO);
    void deleteTravelPackage(Long id);
}