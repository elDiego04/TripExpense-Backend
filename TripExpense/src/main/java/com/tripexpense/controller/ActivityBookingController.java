package com.tripexpense.controller;

import com.tripexpense.dto.ActivityBookingDTO;
import com.tripexpense.repository.ActivityBookingRepository;
import com.tripexpense.service.impl.ActivityBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-bookings")
public class ActivityBookingController {

    @Autowired
    private ActivityBookingServiceImpl bookingService;

    @Autowired
    private ActivityBookingRepository bookingRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createBooking(@RequestBody ActivityBookingDTO bookingDTO) {
        try {
            ActivityBookingDTO createdBooking = bookingService.createBooking(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la reserva: " + e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllBookings() {
        try {
            List<ActivityBookingDTO> bookings = bookingService.getAllBookings();

            if (bookings == null || bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No hay reservas registradas");
            }

            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las reservas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            ActivityBookingDTO booking = bookingService.getBookingById(id);

            if (booking == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reserva no encontrada con ID: " + id);
            }

            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la reserva: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
        try {
            List<ActivityBookingDTO> bookings = bookingService.getBookingsByUserId(userId);

            if (bookings == null || bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No hay reservas para este usuario");
            }

            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener reservas del usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            if (!bookingRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reserva con ID " + id + " no existe");
            }

            bookingService.deleteBooking(id);
            return ResponseEntity.ok("Reserva eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}
