package com.tripexpense.controller;

import com.tripexpense.dto.TravelPlanDTO;
import com.tripexpense.repository.TravelPlanRepository;
import com.tripexpense.service.impl.TravelPlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-plans")
public class TravelPlanController {

    @Autowired
    private TravelPlanServiceImpl travelPlanService;

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @PostMapping
    public ResponseEntity<?> createTravelPlan(@RequestBody TravelPlanDTO travelPlanDTO) {
        try {
            TravelPlanDTO createdPlan = travelPlanService.createTravelPlan(travelPlanDTO);

            if (createdPlan == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear el plan de viaje");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTravelPlans() {
        try {
            List<TravelPlanDTO> planDTOs = travelPlanService.getAllTravelPlans();

            if (planDTOs == null || planDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay planes de viaje registrados");
            }

            return ResponseEntity.ok(planDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los planes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTravelPlanById(@PathVariable Long id) {
        try {
            TravelPlanDTO planDTO = travelPlanService.getTravelPlanById(id);

            if (planDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el plan de viaje");
            }

            return ResponseEntity.ok(planDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener el plan: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTravelPlansByUser(@PathVariable Long userId) {
        try {
            List<TravelPlanDTO> planDTOs = travelPlanService.getTravelPlansByUser(userId);

            if (planDTOs == null || planDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay planes para este usuario");
            }

            return ResponseEntity.ok(planDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener planes por usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTravelPlan(@PathVariable Long id, @RequestBody TravelPlanDTO travelPlanDTO) {
        try {
            TravelPlanDTO updatedPlan = travelPlanService.updateTravelPlan(id, travelPlanDTO);

            if (updatedPlan == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el plan de viaje");
            }

            return ResponseEntity.ok(updatedPlan);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar el plan: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTravelPlan(@PathVariable Long id) {
        try {
            if (!travelPlanRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Plan con ID " + id + " no existe");
            }

            travelPlanService.deleteTravelPlan(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Plan eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el plan: " + e.getMessage());
        }
    }

    @PostMapping("/{planId}/activities/{activityId}")
    public ResponseEntity<?> addActivityToPlan(@PathVariable Long planId, @PathVariable Long activityId) {
        try {
            TravelPlanDTO updatedPlan = travelPlanService.addActivityToPlan(planId, activityId);

            if (updatedPlan == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se pudo agregar la actividad al plan");
            }

            return ResponseEntity.ok(updatedPlan);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar actividad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{planId}/activities/{activityId}")
    public ResponseEntity<?> removeActivityFromPlan(@PathVariable Long planId, @PathVariable Long activityId) {
        try {
            TravelPlanDTO updatedPlan = travelPlanService.removeActivityFromPlan(planId, activityId);

            if (updatedPlan == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se pudo remover la actividad del plan");
            }

            return ResponseEntity.ok(updatedPlan);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al remover actividad: " + e.getMessage());
        }
    }
}