package com.tripexpense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @NotBlank
    @Size(max = 255)
    private String address;

    @Size(max = 255)
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @Min(1)
    @Max(5)
    private Integer stars;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> amenities;

    @NotBlank
    @Size(max = 50)
    private String checkInTime;

    @NotBlank
    @Size(max = 50)
    private String checkOutTime;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phone;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Hotel(){}

    public Hotel(Long hotelId, String name, City city, String address, String imageUrl, Integer stars, String description, List<String> amenities, String checkInTime, String checkOutTime, String email, String phone, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.imageUrl = imageUrl;
        this.stars = stars;
        this.description = description;
        this.amenities = amenities;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}