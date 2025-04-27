package com.tripexpense.controller;

import com.tripexpense.dto.UserDTO;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El usuario ya existe");
            }

            UserDTO createdUser = userService.createUser(userDTO);

            if (createdUser == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear el usuario");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> userDTOS = userService.getAllUsers();

            if (userDTOS == null || userDTOS.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("No hay usuarios registrados.");
            }

            return ResponseEntity.ok(userDTOS);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los usuarios: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            UserDTO userDTO = userService.getUserById(id);

            if (userDTO == null){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontro ningún usuario");
            }

            return ResponseEntity.ok(userDTO);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los usuarios: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        try {
            UserDTO updateUser = userService.updateUser(id, userDTO);

            if (userDTO == null){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontro ningún usuario");
            }

            return ResponseEntity.ok(userDTO);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener los usuarios: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            if (!userRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Usuario con ID " + id + " no existe.");
            }

            userService.deleteUser(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Usuario eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el usuario: " + e.getMessage());
        }
    }


}
