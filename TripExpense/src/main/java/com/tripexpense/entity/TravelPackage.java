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
    @Column(name = "travel_package_id")
    private Long id;

    @NotBlank(message = "El nombre del paquete es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 255, message = "La URL no puede superar los 255 caracteres")
    @URL(message = "La URL de la imagen debe ser válida")
    private String imageUrl;

    @NotNull(message = "La ciudad destino es obligatoria")
    @ManyToOne
    @JoinColumn(name = "destination_city_id", nullable = false)
    private City destination;

    @NotNull(message = "El precio base es obligatorio")
    @PositiveOrZero(message = "El precio debe ser igual o mayor a cero")
    private Double basePrice;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser de al menos 1 día")
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
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> includedActivities;

    @NotNull(message = "El tipo de paquete es obligatorio")
    @Enumerated(EnumType.STRING)
    private PackageType type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TravelPackage() {}

    public TravelPackage(Long id, String name, String description, String imageUrl, City destination,
                         Double basePrice, Integer durationDays, Flight includedFlight, Hotel includedHotel,
                         List<Activity> includedActivities, PackageType type,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public City getDestination() { return destination; }

    public void setDestination(City destination) { this.destination = destination; }

    public Double getBasePrice() { return basePrice; }

    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Integer getDurationDays() { return durationDays; }

    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }

    public Flight getIncludedFlight() { return includedFlight; }

    public void setIncludedFlight(Flight includedFlight) { this.includedFlight = includedFlight; }

    public Hotel getIncludedHotel() { return includedHotel; }

    public void setIncludedHotel(Hotel includedHotel) { this.includedHotel = includedHotel; }

    public List<Activity> getIncludedActivities() { return includedActivities; }

    public void setIncludedActivities(List<Activity> includedActivities) { this.includedActivities = includedActivities; }

    public PackageType getType() { return type; }

    public void setType(PackageType type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
