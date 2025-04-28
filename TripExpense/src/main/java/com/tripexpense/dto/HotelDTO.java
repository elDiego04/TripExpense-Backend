package com.tripexpense.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;

import java.util.List;

public class HotelDTO {

    private Long hotelId;

    @NotNull
    private CityDTO city;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String address;

    @Min(1) @Max(5)
    private Integer starRating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> amenities;

    @ElementCollection
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
    private String contactEmail;

    @Size(max = 20)
    private String contactPhone;

    public HotelDTO(){}

    public HotelDTO(Long hotelId, CityDTO city, String name, String address, Integer starRating, String description, List<String> amenities, List<String> roomTypes, Double pricePerNight, Integer maxAdults, Integer maxChildren, String checkInTime, String checkOutTime, String contactEmail, String contactPhone) {
        this.hotelId = hotelId;
        this.city = city;
        this.name = name;
        this.address = address;
        this.starRating = starRating;
        this.description = description;
        this.amenities = amenities;
        this.roomTypes = roomTypes;
        this.pricePerNight = pricePerNight;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
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

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
