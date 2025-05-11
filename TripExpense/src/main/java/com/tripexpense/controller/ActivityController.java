package com.tripexpense.controller;

import com.tripexpense.dto.ActivityDTO;
import com.tripexpense.repository.ActivityRepository;
import com.tripexpense.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createActivity(@RequestBody ActivityDTO activityDTO) {
        try {
            if (activityRepository.existsByNameAndCityCityId(activityDTO.getName(), activityDTO.getCityId())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("La actividad ya existe en esta ciudad");
            }

            ActivityDTO createdActivity = activityService.createActivity(activityDTO);

            if (createdActivity == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear la actividad");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllActivities() {
        try {
            List<ActivityDTO> activityDTOs = activityService.getAllActivities();

            if (activityDTOs == null || activityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay actividades registradas");
            }

            return ResponseEntity.ok(activityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener las actividades: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable Long id) {
        try {
            ActivityDTO activityDTO = activityService.getActivityById(id);

            if (activityDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la actividad");
            }

            return ResponseEntity.ok(activityDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener la actividad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        try {
            ActivityDTO updatedActivity = activityService.updateActivity(id, activityDTO);

            if (updatedActivity == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la actividad");
            }

            return ResponseEntity.ok(updatedActivity);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar la actividad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        try {
            if (!activityRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Actividad con ID " + id + " no existe");
            }

            activityService.deleteActivity(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Actividad eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la actividad: " + e.getMessage());
        }
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<?> getActivitiesByCity(@PathVariable Long cityId) {
        try {
            List<ActivityDTO> activityDTOs = activityService.getActivitiesByCity(cityId);

            if (activityDTOs == null || activityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay actividades en esta ciudad");
            }

            return ResponseEntity.ok(activityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener actividades por ciudad: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchActivities(@RequestParam String query) {
        try {
            List<ActivityDTO> activityDTOs = activityService.searchActivities(query);

            if (activityDTOs == null || activityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No se encontraron resultados");
            }

            return ResponseEntity.ok(activityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la búsqueda: " + e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getActivitiesByCategory(@PathVariable String category) {
        try {
            List<ActivityDTO> activityDTOs = activityService.findActivitiesByCategory(category);

            if (activityDTOs == null || activityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay actividades en esta categoría");
            }

            return ResponseEntity.ok(activityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener actividades por categoría: " + e.getMessage());
        }
    }
}