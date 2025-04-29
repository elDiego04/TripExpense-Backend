package com.tripexpense.controller;

import com.tripexpense.dto.HotelDTO;
import com.tripexpense.repository.HotelRepository;
import com.tripexpense.service.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO) {
        try {
            if (hotelRepository.existsByNameAndCityCityId(hotelDTO.getName(), hotelDTO.getCityId())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El hotel ya existe en esta ciudad");
            }

            HotelDTO createdHotel = hotelService.createHotel(hotelDTO);

            if (createdHotel == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear el hotel");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        try {
            List<HotelDTO> hotelDTOs = hotelService.getAllHotels();

            if (hotelDTOs == null || hotelDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay hoteles registrados");
            }

            return ResponseEntity.ok(hotelDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los hoteles: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id) {
        try {
            HotelDTO hotelDTO = hotelService.getHotelById(id);

            if (hotelDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el hotel");
            }

            return ResponseEntity.ok(hotelDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener el hotel: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        try {
            HotelDTO updatedHotel = hotelService.updateHotel(id, hotelDTO);

            if (updatedHotel == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el hotel");
            }

            return ResponseEntity.ok(updatedHotel);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar el hotel: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        try {
            if (!hotelRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Hotel con ID " + id + " no existe");
            }

            hotelService.deleteHotel(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Hotel eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el hotel: " + e.getMessage());
        }
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<?> getHotelsByCity(@PathVariable Long cityId) {
        try {
            List<HotelDTO> hotelDTOs = hotelService.getHotelsByCity(cityId);

            if (hotelDTOs == null || hotelDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay hoteles en esta ciudad");
            }

            return ResponseEntity.ok(hotelDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener hoteles por ciudad: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchHotels(@RequestParam String query) {
        try {
            List<HotelDTO> hotelDTOs = hotelService.searchHotels(query);

            if (hotelDTOs == null || hotelDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No se encontraron resultados");
            }

            return ResponseEntity.ok(hotelDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la búsqueda: " + e.getMessage());
        }
    }
}