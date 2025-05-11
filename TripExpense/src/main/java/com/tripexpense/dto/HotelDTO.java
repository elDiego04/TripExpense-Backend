package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class HotelDTO {
    private Long hotelId;
    private String cityName;

    private Long cityId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String address;

    @Size(max = 255)
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @Min(1) @Max(5)
    private Integer stars;

    @Size(max = 2000)
    private String description;

    private List<String> amenities;
    private List<String> roomTypes;

    @NotNull
    @PositiveOrZero
    private Double pricePerNight;

    @NotNull
    @Min(1)
    private Integer maxAdults;

    @NotNull
    @Min(0)
    private Integer maxChildren;

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
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Teléfono debe tener entre 10 y 15 dígitos")
    private String phone;

    public HotelDTO() {}

    public HotelDTO(Long hotelId, String cityName, Long cityId, String name, String address, String imageUrl, Integer stars, String description, List<String> amenities, List<String> roomTypes, Double pricePerNight, Integer maxAdults, Integer maxChildren, String checkInTime, String checkOutTime, String email, String phone) {
        this.hotelId = hotelId;
        this.cityName = cityName;
        this.cityId = cityId;
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.stars = stars;
        this.description = description;
        this.amenities = amenities;
        this.roomTypes = roomTypes;
        this.pricePerNight = pricePerNight;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.email = email;
        this.phone = phone;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<String> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(Integer maxChildren) {
        this.maxChildren = maxChildren;
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
}