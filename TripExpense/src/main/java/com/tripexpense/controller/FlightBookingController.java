package com.tripexpense.controller;

import com.tripexpense.dto.FlightBookingDTO;
import com.tripexpense.service.impl.FlightBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight-bookings")
public class FlightBookingController {

    @Autowired
    private FlightBookingServiceImpl flightBookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody FlightBookingDTO dto) {
        try {
            FlightBookingDTO created = flightBookingService.createFlightBooking(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la reserva de vuelo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        try {
            List<FlightBookingDTO> bookings = flightBookingService.getAllFlightBookings();
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay reservas de vuelos.");
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las reservas de vuelos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            FlightBookingDTO booking = flightBookingService.getFlightBookingById(id);
            if (booking == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada.");
            }
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la reserva de vuelo: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
        try {
            List<FlightBookingDTO> bookings = flightBookingService.getFlightBookingsByUserId(userId);
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Este usuario no tiene reservas de vuelos.");
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las reservas por usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            flightBookingService.deleteFlightBooking(id);
            return ResponseEntity.ok("Reserva de vuelo eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}
