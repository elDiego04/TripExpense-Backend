package com.tripexpense.controller;

import com.tripexpense.dto.FlightDTO;
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

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO createdFlight = flightService.createFlight(flightDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el vuelo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        try {
            List<FlightDTO> flights = flightService.getAllFlights();
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay vuelos disponibles.");
            }
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los vuelos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        try {
            FlightDTO flight = flightService.getFlightById(id);
            if (flight == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vuelo no encontrado.");
            }
            return ResponseEntity.ok(flight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el vuelo: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO updatedFlight = flightService.updateFlight(id, flightDTO);
            if (updatedFlight == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vuelo no encontrado.");
            }
            return ResponseEntity.ok(updatedFlight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el vuelo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok("Vuelo eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el vuelo: " + e.getMessage());
        }
    }
}
