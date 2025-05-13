package com.tripexpense.entity;

import com.tripexpense.enums.FlightClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_bookings")
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

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
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public FlightBooking() {}

    public FlightBooking(Long id, Flight flight, Double price, Integer adults, Integer children, FlightClass flightClass, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.flight = flight;
        this.price = price;
        this.adults = adults;
        this.children = children;
        this.flightClass = flightClass;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
