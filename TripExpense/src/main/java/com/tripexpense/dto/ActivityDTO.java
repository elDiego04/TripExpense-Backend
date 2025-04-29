package com.tripexpense.dto;

import java.util.List;

public class ActivityDTO {

    private Long activityId;
    private String name;
    private String description;
    private Long cityId;
    private String cityName;
    private String category;
    private Integer duration; // in minutes
    private Double price;
    private String location;
    private List<String> availabilityDates;
    private Integer minPeople;
    private Integer maxPeople;
    private String difficultyLevel;

    public ActivityDTO(){}

    public ActivityDTO(Long activityId, String name, String description, Long cityId, String cityName, String category, Integer duration, Double price, String location, List<String> availabilityDates, Integer minPeople, Integer maxPeople, String difficultyLevel) {
        this.activityId = activityId;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.cityName = cityName;
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
