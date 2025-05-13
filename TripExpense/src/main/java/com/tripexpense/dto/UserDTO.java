package com.tripexpense.dto;

import com.tripexpense.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class UserDTO {
    private Long Id;

    @Email(message = "Email no válido")
    @NotBlank(message = "Email es obligatorio")
    private String email;

    @NotBlank(message = "Contraseña es obligatoria")
    @Size(min = 7, message = "La contraseña debe tener al menos 7 caracteres")
    private String password;

    @NotBlank(message = "Nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "Apellido es obligatorio")
    private String lastName;

    @Size(max = 255)
    @URL(message = "La URL del avatar debe ser válida")
    private String avatarUrl;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Teléfono debe tener entre 10 y 15 dígitos")
    private String phone;

    @Column(nullable = false)
    private Role role;

    public UserDTO() {}

    public UserDTO(Long id, String email, String password, String firstName, String lastName, String avatarUrl, String phone, Role role) {
        Id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}