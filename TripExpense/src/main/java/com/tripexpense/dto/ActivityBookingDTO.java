package com.tripexpense.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ActivityBookingDTO {

    private Long activityBookingId;
    @NotNull(message = "El ID de la actividad es obligatorio")
    private Long activityId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "Debe indicar el número de personas")
    @Min(value = 1, message = "Debe haber al menos una persona")
    private Integer numberOfPeople;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @Future(message = "La fecha de reserva debe ser en el futuro")
    private LocalDateTime bookingDate;

    @NotNull(message = "El precio total es obligatorio")
    @Positive(message = "El precio total debe ser mayor que cero")
    private Double totalPrice;

    public ActivityBookingDTO(){}

    public ActivityBookingDTO(Long activityBookingId, Long activityId, Long userId, Integer numberOfPeople, LocalDateTime bookingDate, Double totalPrice) {
        this.activityBookingId = activityBookingId;
        this.activityId = activityId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
    }

    public Long getActivityBookingId() {
        return activityBookingId;
    }

    public void setActivityBookingId(Long activityBookingId) {
        this.activityBookingId = activityBookingId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
