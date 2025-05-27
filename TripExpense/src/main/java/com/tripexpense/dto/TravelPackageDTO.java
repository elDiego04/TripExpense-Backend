package com.tripexpense.dto;

import com.tripexpense.enums.PackageType;
import jakarta.validation.constraints.*;

import java.util.List;

public class TravelPackageDTO {

    private Long travelPackageId;

    @NotBlank(message = "El nombre del paquete es obligatorio")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imageUrl;

    @NotNull(message = "La ciudad de destino es obligatoria")
    private Long destinationCityId;

    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio base debe ser mayor a 0")
    private Double basePrice;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser mayor a 0")
    private Integer durationDays;

    @NotNull(message = "Debe incluir un vuelo")
    private Long includedFlightId;

    @NotNull(message = "Debe incluir un hotel")
    private Long includedHotelId;

    @NotNull(message = "Debe incluir al menos una actividad")
    @Size(min = 1, message = "Debe incluir al menos una actividad")
    private List<Long> includedActivityIds;

    @NotNull(message = "El tipo de paquete es obligatorio")
    private PackageType type;

    public TravelPackageDTO() {}

    public TravelPackageDTO(Long travelPackageId, String name, String description, String imageUrl,
                            Long destinationCityId, Double basePrice, Integer durationDays,
                            Long includedFlightId, Long includedHotelId, List<Long> includedActivityIds,
                            PackageType type) {
        this.travelPackageId = travelPackageId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.destinationCityId = destinationCityId;
        this.basePrice = basePrice;
        this.durationDays = durationDays;
        this.includedFlightId = includedFlightId;
        this.includedHotelId = includedHotelId;
        this.includedActivityIds = includedActivityIds;
        this.type = type;
    }

    public Long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(Long travelPackageId) {
        this.travelPackageId = travelPackageId;
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

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public Long getIncludedFlightId() {
        return includedFlightId;
    }

    public void setIncludedFlightId(Long includedFlightId) {
        this.includedFlightId = includedFlightId;
    }

    public Long getIncludedHotelId() {
        return includedHotelId;
    }

    public void setIncludedHotelId(Long includedHotelId) {
        this.includedHotelId = includedHotelId;
    }

    public List<Long> getIncludedActivityIds() {
        return includedActivityIds;
    }

    public void setIncludedActivityIds(List<Long> includedActivityIds) {
        this.includedActivityIds = includedActivityIds;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }
}
