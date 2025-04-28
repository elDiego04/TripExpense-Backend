package com.tripexpense.entity;

import com.tripexpense.enums.FlightClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotBlank(message = "La aerolínea es obligatoria")
    @Size(max = 100)
    private String airline;

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
    private LocalTime departureTime;

    @NotNull(message = "La hora de llegada es obligatoria")
    private LocalTime arrivalTime;

    @NotNull(message = "La fecha de salida es obligatoria")
    @FutureOrPresent(message = "La fecha de salida debe ser hoy o en el futuro")
    private LocalDate departureDate;

    @Future(message = "La fecha de regreso debe ser en el futuro")
    private LocalDate returnDate;

    @NotNull
    @Positive(message = "La duración debe ser positiva")
    private Integer duration;

    @NotNull
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double price;

    @NotNull
    @Min(value = 1, message = "Debe haber al menos 1 adulto")
    private Integer adultNumber;

    @NotNull
    @Min(value = 0, message = "El número de niños no puede ser negativa")
    private Integer childNumber;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Flight(){}

    public Flight(Long flightId, String airline, String flightNumber, City departureCity, City arrivalCity, LocalTime departureTime, LocalTime arrivalTime, LocalDate departureDate, LocalDate returnDate, Integer duration, Double price, Integer adultNumber, Integer childNumber, FlightClass flightClass, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.flightId = flightId;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.duration = duration;
        this.price = price;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
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

    public Integer getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
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

