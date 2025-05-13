package com.tripexpense.controller;


import com.tripexpense.dto.LoginRequestDTO;
import com.tripexpense.dto.LoginResponseDTO;
import com.tripexpense.dto.UserDTO;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("El usuario ya existe");
            }

            UserDTO registeredUser = userService.registerUser(userDTO);

            if (registeredUser == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo crear el usuario");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            if (!userRepository.existsByEmail(loginRequestDTO.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("El usuario no existe");
            }

            LoginResponseDTO loginResponse = userService.loginUser(loginRequestDTO);

            if (loginResponse == null) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Credenciales incorrectas");
            }

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }
}
