package com.tripexpense.controller;

import com.tripexpense.dto.TravelPackageDTO;
import com.tripexpense.repository.TravelPackageRepository;
import com.tripexpense.service.impl.TravelPackageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-packages")
public class TravelPackageController {

    @Autowired private TravelPackageServiceImpl travelPackageService;
    @Autowired private TravelPackageRepository travelPackageRepository;

    @PostMapping
    public ResponseEntity<?> createTravelPackage(@RequestBody TravelPackageDTO dto) {
        try {
            TravelPackageDTO created = travelPackageService.createTravelPackage(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el paquete: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTravelPackages() {
        try {
            List<TravelPackageDTO> list = travelPackageService.getAllTravelPackages();
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay paquetes registrados.");
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los paquetes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            TravelPackageDTO dto = travelPackageService.getTravelPackageById(id);
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paquete no encontrado.");
            }
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el paquete: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TravelPackageDTO dto) {
        try {
            TravelPackageDTO updated = travelPackageService.updateTravelPackage(id, dto);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paquete no encontrado.");
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el paquete: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!travelPackageRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paquete no encontrado.");
            }
            travelPackageService.deleteTravelPackage(id);
            return ResponseEntity.ok("Paquete eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el paquete: " + e.getMessage());
        }
    }
}