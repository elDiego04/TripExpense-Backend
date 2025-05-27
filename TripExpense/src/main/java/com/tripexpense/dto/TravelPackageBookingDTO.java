package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TravelPackageBookingDTO {

    private Long id;
    @NotNull(message = "El ID del paquete turístico es obligatorio")
    private Long travelPackageId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    @NotNull(message = "La fecha final es obligatoria")
    private LocalDate endDate;

    @NotNull(message = "El número de adultos es obligatorio")
    @Min(1)
    private Integer numberOfAdults;

    @Min(0)
    private Integer numberOfChildren;

    @PositiveOrZero
    private Double totalPrice;

    public TravelPackageBookingDTO(){}

    public TravelPackageBookingDTO(Long id,Long travelPackageId, Long userId, LocalDate startDate, LocalDate endDate, Integer numberOfAdults, Integer numberOfChildren, Double totalPrice) {
        this.id = id;
        this.travelPackageId = travelPackageId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(Long travelPackageId) {
        this.travelPackageId = travelPackageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
