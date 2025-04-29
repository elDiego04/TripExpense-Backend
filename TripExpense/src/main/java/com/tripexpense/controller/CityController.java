package com.tripexpense.controller;

import com.tripexpense.dto.CityDTO;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CityRepository cityRepository;

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody CityDTO cityDTO) {
        try {
            if (cityRepository.existsByNameAndCountry(cityDTO.getName(), cityDTO.getCountry())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("La ciudad ya existe");
            }

            CityDTO createdCity = cityService.createCity(cityDTO);

            if (createdCity == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear la ciudad");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        try {
            List<CityDTO> cityDTOs = cityService.getAllCities();

            if (cityDTOs == null || cityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay ciudades registradas");
            }

            return ResponseEntity.ok(cityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener las ciudades: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Long id) {
        try {
            CityDTO cityDTO = cityService.getCityById(id);

            if (cityDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la ciudad");
            }

            return ResponseEntity.ok(cityDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener la ciudad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        try {
            CityDTO updatedCity = cityService.updateCity(id, cityDTO);

            if (updatedCity == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la ciudad");
            }

            return ResponseEntity.ok(updatedCity);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar la ciudad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        try {
            if (!cityRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Ciudad con ID " + id + " no existe");
            }

            cityService.deleteCity(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Ciudad eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la ciudad: " + e.getMessage());
        }
    }

    /*@GetMapping("/search")
    public ResponseEntity<?> searchCities(@RequestParam String query) {
        try {
            List<CityDTO> cityDTOs = cityService.searchCities(query);

            if (cityDTOs == null || cityDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No se encontraron resultados");
            }

            return ResponseEntity.ok(cityDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la búsqueda: " + e.getMessage());
        }
    }*/
}