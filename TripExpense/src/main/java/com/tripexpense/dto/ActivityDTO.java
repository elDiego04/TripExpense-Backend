package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class ActivityDTO {
    private Long activityId;

    @NotNull(message = "La ciudad es obligatoria")
    private Long cityId;

    private String cityName;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @Size(max = 255)
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder los 50 caracteres")
    private String category;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser un número positivo")
    private Integer duration;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double price;

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 255, message = "La ubicación no puede exceder los 255 caracteres")
    private String location;

    private List<String> availabilityDates;

    @NotNull(message = "El mínimo de personas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    private Integer minPeople;

    @NotNull(message = "El máximo de personas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    private Integer maxPeople;

    @Size(max = 50, message = "El nivel de dificultad no puede exceder los 50 caracteres")
    private String difficultyLevel;

    // Constructores
    public ActivityDTO() {
    }

    public ActivityDTO(Long activityId, Long cityId, String cityName, String name, String description,
                       String imageUrl, String category, Integer duration, Double price,
                       String location, List<String> availabilityDates, Integer minPeople,
                       Integer maxPeople, String difficultyLevel) {
        this.activityId = activityId;
        this.cityId = cityId;
        this.cityName = cityName;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.duration = duration;
        this.price = price;
        this.location = location;
        this.availabilityDates = availabilityDates;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters y Setters
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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