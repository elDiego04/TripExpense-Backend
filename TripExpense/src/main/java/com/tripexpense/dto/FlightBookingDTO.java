package com.tripexpense.dto;

import com.tripexpense.enums.FlightClass;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class FlightBookingDTO {

    private Long id;

    @NotNull(message = "El ID del vuelo es obligatorio")
    private Long flightId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double price;

    @Min(value = 1, message = "Debe haber al menos 1 adulto")
    private Integer adults;

    @Min(value = 0, message = "El número de niños no puede ser negativo")
    private Integer children;

    private FlightClass flightClass;

    public FlightBookingDTO(){}

    public FlightBookingDTO(Long id, Long flightId, Long userId, Double price, Integer adults, Integer children, FlightClass flightClass) {
        this.id = id;
        this.flightId = flightId;
        this.userId = userId;
        this.price = price;
        this.adults = adults;
        this.children = children;
        this.flightClass = flightClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
