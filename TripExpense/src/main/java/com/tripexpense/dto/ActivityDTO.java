package com.tripexpense.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;

import java.util.List;

public class ActivityDTO {

    private Long activityId;

    @NotNull
    private CityDTO city;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotNull
    @Positive
    private Integer duration;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotBlank
    @Size(max = 255)
    private String location;

    @ElementCollection
    private List<String> availabilityDates;

    @NotNull
    @Min(1)
    private Integer minPeople;

    @NotNull
    @Min(1)
    private Integer maxPeople;

    @Size(max = 50)
    private String difficultyLevel;

    public ActivityDTO(){}

    public ActivityDTO(Long activityId, CityDTO city, String name, String description, String category, Integer duration, Double price, String location, List<String> availabilityDates, Integer minPeople, Integer maxPeople, String difficultyLevel) {
        this.activityId = activityId;
        this.city = city;
        this.name = name;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.price = price;
        this.location = location;
        this.availabilityDates = availabilityDates;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(List<String> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }

    public Integer getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
