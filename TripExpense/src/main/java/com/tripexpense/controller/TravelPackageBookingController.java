package com.tripexpense.controller;

import com.tripexpense.dto.TravelPackageBookingDTO;
import com.tripexpense.repository.TravelPackageBookingRepository;
import com.tripexpense.service.impl.TravelPackageBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-package-bookings")
public class TravelPackageBookingController {

    @Autowired
    private TravelPackageBookingServiceImpl bookingService;

    @Autowired
    private TravelPackageBookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody TravelPackageBookingDTO dto) {
        try {
            TravelPackageBookingDTO created = bookingService.createBooking(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la reserva: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        try {
            List<TravelPackageBookingDTO> bookings = bookingService.getAllBookings();
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay reservas registradas.");
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener reservas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            TravelPackageBookingDTO booking = bookingService.getBookingById(id);
            if (booking == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener reserva: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
        try {
            List<TravelPackageBookingDTO> bookings = bookingService.getBookingsByUserId(userId);
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron reservas para este usuario");
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener reservas: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody TravelPackageBookingDTO dto) {
        try {
            TravelPackageBookingDTO updated = bookingService.updateBooking(id, dto);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            if (!bookingRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
            }
            bookingService.deleteBooking(id);
            return ResponseEntity.ok("Reserva eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar reserva: " + e.getMessage());
        }
    }
}
