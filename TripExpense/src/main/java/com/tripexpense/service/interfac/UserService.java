package com.tripexpense.service.interfac;

import com.tripexpense.dto.LoginRequestDTO;
import com.tripexpense.dto.LoginResponseDTO;
import com.tripexpense.dto.UserDTO;

import java.util.List;

public interface UserService{

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

    UserDTO registerUser(UserDTO userDTO);

    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
}
