package com.tripexpense.controller;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.repository.FlightRepository;
import com.tripexpense.service.impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody FlightDTO flightDTO) {
        try {
            if (flightRepository.existsByFlightNumber(flightDTO.getFlightNumber())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El número de vuelo ya existe");
            }

            FlightDTO createdFlight = flightService.createFlight(flightDTO);

            if (createdFlight == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear el vuelo");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        try {
            List<FlightDTO> flightDTOs = flightService.getAllFlights();

            if (flightDTOs == null || flightDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay vuelos registrados");
            }

            return ResponseEntity.ok(flightDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los vuelos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        try {
            FlightDTO flightDTO = flightService.getFlightById(id);

            if (flightDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el vuelo");
            }

            return ResponseEntity.ok(flightDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener el vuelo: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO updatedFlight = flightService.updateFlight(id, flightDTO);

            if (updatedFlight == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el vuelo");
            }

            return ResponseEntity.ok(updatedFlight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar el vuelo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            if (!flightRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Vuelo con ID " + id + " no existe");
            }

            flightService.deleteFlight(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Vuelo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el vuelo: " + e.getMessage());
        }
    }

    /*@PostMapping("/search")
    public ResponseEntity<?> searchFlights(@RequestBody FlightSearchDTO searchDTO) {
        try {
            List<FlightDTO> flightDTOs = flightService.searchFlights(searchDTO);

            if (flightDTOs == null || flightDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No se encontraron vuelos disponibles");
            }

            return ResponseEntity.ok(flightDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la búsqueda: " + e.getMessage());
        }
    }*/
}