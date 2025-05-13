package com.tripexpense.entity;

import com.tripexpense.enums.PackageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "travel_packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelPackageId;

    @NotBlank(message = "El nombre del paquete es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 255)
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destination_city_id")
    private City destination;

    @NotNull
    @PositiveOrZero
    private Double basePrice;

    @NotNull
    @Min(1)
    private Integer durationDays;

    @ManyToOne
    @JoinColumn(name = "included_flight_id")
    private Flight includedFlight;

    @ManyToOne
    @JoinColumn(name = "included_hotel_id")
    private Hotel includedHotel;

    @ManyToMany
    @JoinTable(
            name = "package_activities",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> includedActivities;


    @NotNull
    @Enumerated(EnumType.STRING)
    private PackageType type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TravelPackage(){}

    public TravelPackage(Long travelPackageId, String name, String description, String imageUrl, City destination, Double basePrice, Integer durationDays, Flight includedFlight, Hotel includedHotel, List<Activity> includedActivities, PackageType type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.travelPackageId = travelPackageId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.destination = destination;
        this.basePrice = basePrice;
        this.durationDays = durationDays;
        this.includedFlight = includedFlight;
        this.includedHotel = includedHotel;
        this.includedActivities = includedActivities;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
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

    public Flight getIncludedFlight() {
        return includedFlight;
    }

    public void setIncludedFlight(Flight includedFlight) {
        this.includedFlight = includedFlight;
    }

    public Hotel getIncludedHotel() {
        return includedHotel;
    }

    public void setIncludedHotel(Hotel includedHotel) {
        this.includedHotel = includedHotel;
    }

    public List<Activity> getIncludedActivities() {
        return includedActivities;
    }

    public void setIncludedActivities(List<Activity> includedActivities) {
        this.includedActivities = includedActivities;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
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