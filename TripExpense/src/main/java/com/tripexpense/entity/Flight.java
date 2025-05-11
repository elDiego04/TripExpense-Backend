package com.tripexpense.entity;

import com.tripexpense.enums.FlightClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotBlank(message = "La aerolínea es obligatoria")
    @Size(max = 100)
    private String airline;

    @Size(max = 255)
    @URL(message = "La URL del logo debe ser válida")
    private String airlineLogoUrl;

    @NotBlank(message = "El número de vuelo es obligatorio")
    @Size(max = 20)
    private String flightNumber;

    @NotNull(message = "La ciudad de origen es obligatoria")
    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @NotNull(message = "La ciudad de destino es obligatoria")
    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @NotNull(message = "La hora de salida es obligatoria")
    private LocalDateTime departureDateTime;

    @NotNull(message = "La hora de llegada es obligatoria")
    private LocalDateTime arrivalDateTime;

    @NotNull
    @Positive(message = "La duración en minutos debe ser positiva")
    private Integer durationMinutes;

    @NotNull
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double price;

    @Min(value = 1, message = "Debe haber al menos 1 adulto")
    private Integer adults;

    @Min(value = 0, message = "El número de niños no puede ser negativo")
    private Integer children;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void validateCities() {
        if (departureCity != null && departureCity.equals(arrivalCity)) {
            throw new IllegalArgumentException("La ciudad de origen y destino no pueden ser iguales");
        }
    }
    public Flight(){}

    public Flight(Long id, String airline, String airlineLogoUrl, String flightNumber, City departureCity, City arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Integer durationMinutes, Double price, Integer adults, Integer children, FlightClass flightClass, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.flightId = id;
        this.airline = airline;
        this.airlineLogoUrl = airlineLogoUrl;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.adults = adults;
        this.children = children;
        this.flightClass = flightClass;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirlineLogoUrl() {
        return airlineLogoUrl;
    }

    public void setAirlineLogoUrl(String airlineLogoUrl) {
        this.airlineLogoUrl = airlineLogoUrl;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
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

