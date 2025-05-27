package com.tripexpense.service.interfac;

import com.tripexpense.dto.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {
    TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO);
    List<TravelPackageDTO> getAllTravelPackages();
    TravelPackageDTO getTravelPackageById(Long id);
    TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO);
    void deleteTravelPackage(Long id);
}
