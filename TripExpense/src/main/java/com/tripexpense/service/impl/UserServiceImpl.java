package com.tripexpense.service.impl;

import com.tripexpense.dto.LoginRequestDTO;
import com.tripexpense.dto.LoginResponseDTO;
import com.tripexpense.dto.UserDTO;
import com.tripexpense.entity.User;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.interfac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("El email ya se encuentra registrado");
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPassword(userDTO.getPassword());


        return convertToDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return convertToDTO(user.get());
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setPassword(userDTO.getPassword());

            return convertToDTO(userRepository.save(user));
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public void deleteUser(Long id) {

        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("El email ya se encuentra registrado");
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPassword(userDTO.getPassword());

        return convertToDTO(userRepository.save(user));
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginRequestDTO.getPassword())) {
                UserDTO userDTO = convertToDTO(user);
                return new LoginResponseDTO("Login successful", userDTO);
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

    private UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());

        return userDTO;
    }
}
