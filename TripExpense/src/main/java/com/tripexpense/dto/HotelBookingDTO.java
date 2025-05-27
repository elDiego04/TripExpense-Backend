package com.tripexpense.dto;

import com.tripexpense.enums.RoomType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class HotelBookingDTO {

    private Long id;

    @NotNull(message = "El ID del hotel es obligatorio")
    @Positive(message = "El ID del hotel debe ser un número positivo")
    private Long hotelId;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    private Long userId;

    @NotNull(message = "Debe seleccionar al menos un tipo de habitación")
    @Size(min = 1, message = "Debe seleccionar al menos un tipo de habitación")
    private List<RoomType> roomTypes;

    @NotNull(message = "El precio por noche es obligatorio")
    @PositiveOrZero(message = "El precio por noche no puede ser negativo")
    private Double pricePerNight;

    @NotNull(message = "Debe especificar la cantidad máxima de adultos")
    @Min(value = 1, message = "Debe haber al menos un adulto")
    private Integer maxAdults;

    @NotNull(message = "Debe especificar la cantidad máxima de niños")
    @Min(value = 0, message = "La cantidad de niños no puede ser negativa")
    private Integer maxChildren;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede estar en el pasado")
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización es obligatoria")
    @Future(message = "La fecha de finalización debe ser una fecha futura")
    private LocalDate endDate;

    public HotelBookingDTO() {
    }

    public HotelBookingDTO(Long id, Long hotelId, Long userId, List<RoomType> roomTypes, Double pricePerNight,
                           Integer maxAdults, Integer maxChildren, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
        this.roomTypes = roomTypes;
        this.pricePerNight = pricePerNight;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(Integer maxChildren) {
        this.maxChildren = maxChildren;
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
}
