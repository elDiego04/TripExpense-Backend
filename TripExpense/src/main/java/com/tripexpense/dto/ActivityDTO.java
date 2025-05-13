package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class ActivityDTO {

    private Long activityId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private Long cityId;

    @Size(max = 255)
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotNull
    @Positive
    private Integer duration;

    @NotBlank
    @Size(max = 255)
    private String location;

    @Size(max = 50)
    private String difficultyLevel;

    @NotNull
    @PositiveOrZero
    private Double price;

    public ActivityDTO() {}

    public ActivityDTO(Long activityId, String name, Long cityId, String imageUrl, String category, Integer duration, String location, String difficultyLevel, Double price) {
        this.activityId = activityId;
        this.name = name;
        this.cityId = cityId;
        this.imageUrl = imageUrl;
        this.category = category;
        this.duration = duration;
        this.location = location;
        this.difficultyLevel = difficultyLevel;
        this.price = price;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCityName(String name) {
    }
}