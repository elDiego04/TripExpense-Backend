package com.tripexpense.controller;

import com.tripexpense.dto.SearchQueryDTO;
import com.tripexpense.repository.SearchQueryRepository;
import com.tripexpense.service.impl.SearchQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search-queries")
public class SearchQueryController {

    @Autowired
    private SearchQueryServiceImpl searchQueryService;

    @Autowired
    private SearchQueryRepository searchQueryRepository;

    @PostMapping
    public ResponseEntity<?> saveSearchQuery(@RequestBody SearchQueryDTO searchQueryDTO) {
        try {
            SearchQueryDTO savedQuery = searchQueryService.saveSearchQuery(searchQueryDTO);

            if (savedQuery == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo guardar la búsqueda");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedQuery);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSearchQueries() {
        try {
            List<SearchQueryDTO> queryDTOs = searchQueryService.getAllSearchQueries();

            if (queryDTOs == null || queryDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay búsquedas registradas");
            }

            return ResponseEntity.ok(queryDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener las búsquedas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSearchQueryById(@PathVariable Long id) {
        try {
            SearchQueryDTO queryDTO = searchQueryService.getSearchQueryById(id);

            if (queryDTO == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la búsqueda");
            }

            return ResponseEntity.ok(queryDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener la búsqueda: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getSearchQueriesByUser(@PathVariable Long userId) {
        try {
            List<SearchQueryDTO> queryDTOs = searchQueryService.getSearchQueriesByUser(userId);

            if (queryDTOs == null || queryDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay búsquedas para este usuario");
            }

            return ResponseEntity.ok(queryDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener búsquedas por usuario: " + e.getMessage());
        }
    }

    @GetMapping("/recent/{days}")
    public ResponseEntity<?> getRecentSearchQueries(@PathVariable int days) {
        try {
            List<SearchQueryDTO> queryDTOs = searchQueryService.getRecentSearchQueries(days);

            if (queryDTOs == null || queryDTOs.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay búsquedas recientes");
            }

            return ResponseEntity.ok(queryDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener búsquedas recientes: " + e.getMessage());
        }
    }

/*    @GetMapping("/popular-destinations")
    public ResponseEntity<?> getPopularDestinations() {
        try {
            List<PopularSearchDTO> popularDestinations = searchQueryService.getPopularDestinations();

            if (popularDestinations == null || popularDestinations.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay datos de destinos populares");
            }

            return ResponseEntity.ok(popularDestinations);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener destinos populares: " + e.getMessage());
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSearchQuery(@PathVariable Long id) {
        try {
            if (!searchQueryRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Búsqueda con ID " + id + " no existe");
            }

            searchQueryService.deleteSearchQuery(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Búsqueda eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la búsqueda: " + e.getMessage());
        }
    }
}