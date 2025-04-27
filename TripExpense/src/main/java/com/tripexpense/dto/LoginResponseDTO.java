package com.tripexpense.dto;

public class LoginResponseDTO {

    private String message;
    private UserDTO userDTO;

    public LoginResponseDTO(){}
    public LoginResponseDTO(String message, UserDTO userDTO) {
        this.message = message;
        this.userDTO = userDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
