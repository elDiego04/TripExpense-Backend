package com.tripexpense.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CityDTO {

    private Long cityId;

    @NotBlank(message = "El nombre de la ciudad es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 100, message = "El país no puede exceder los 100 caracteres")
    private String country;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 50)
    private String language;

    @Size(max = 10)
    private String currency;

    @Size(max = 50)
    private String climate;

    @Size(max = 100)
    private String bestTimeToVisit;

    public CityDTO(){}
    public CityDTO(Long cityId, String name, String country, String description, String language, String currency, String climate, String bestTimeToVisit) {
        this.cityId = cityId;
        this.name = name;
        this.country = country;
        this.description = description;
        this.language = language;
        this.currency = currency;
        this.climate = climate;
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }
}
