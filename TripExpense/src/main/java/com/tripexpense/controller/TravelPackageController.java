package com.tripexpense.controller;

import com.tripexpense.dto.TravelPackageDTO;
import com.tripexpense.enums.PackageType;
import com.tripexpense.service.interfac.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-packages")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @Autowired
    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TravelPackageDTO> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        TravelPackageDTO createdPackage = travelPackageService.createTravelPackage(travelPackageDTO);
        return new ResponseEntity<>(createdPackage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackageDTO> getTravelPackageById(@PathVariable Long id) {
        TravelPackageDTO travelPackage = travelPackageService.getTravelPackageById(id);
        return ResponseEntity.ok(travelPackage);
    }

    @GetMapping
    public ResponseEntity<List<TravelPackageDTO>> getAllTravelPackages() {
        List<TravelPackageDTO> packages = travelPackageService.getAllTravelPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/destination/{cityId}")
    public ResponseEntity<List<TravelPackageDTO>> getTravelPackagesByDestination(@PathVariable Long cityId) {
        List<TravelPackageDTO> packages = travelPackageService.getTravelPackagesByDestination(cityId);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TravelPackageDTO>> getTravelPackagesByType(@PathVariable PackageType type) {
        List<TravelPackageDTO> packages = travelPackageService.getTravelPackagesByType(type);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TravelPackageDTO>> searchTravelPackages(@RequestParam String query) {
        List<TravelPackageDTO> packages = travelPackageService.searchTravelPackages(query);
        return ResponseEntity.ok(packages);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TravelPackageDTO> updateTravelPackage(
            @PathVariable Long id,
            @RequestBody TravelPackageDTO travelPackageDTO) {
        TravelPackageDTO updatedPackage = travelPackageService.updateTravelPackage(id, travelPackageDTO);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteTravelPackage(@PathVariable Long id) {
        travelPackageService.deleteTravelPackage(id);
        return ResponseEntity.noContent().build();
    }
}