package com.tripexpense.controller;

import com.tripexpense.dto.FlightDTO;
import com.tripexpense.dto.FlightSearchDTO;
import com.tripexpense.service.impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createFlight(@RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO createdFlight = flightService.createFlight(flightDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear vuelo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        try {
            List<FlightDTO> flights = flightService.getAllFlights();
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener vuelos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        try {
            FlightDTO flight = flightService.getFlightById(id);
            return ResponseEntity.ok(flight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Vuelo no encontrado: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO updatedFlight = flightService.updateFlight(id, flightDTO);
            return ResponseEntity.ok(updatedFlight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar vuelo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok().body("Vuelo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar vuelo: " + e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@RequestBody FlightSearchDTO searchDTO) {
        try {
            List<FlightDTO> flights = flightService.searchFlights(searchDTO);
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error en la búsqueda: " + e.getMessage());
        }
    }
}