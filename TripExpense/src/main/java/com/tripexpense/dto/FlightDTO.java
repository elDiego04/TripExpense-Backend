package com.tripexpense.dto;

import com.tripexpense.enums.FlightClass;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class FlightDTO {
    private Long flightId;
    private String departureCityName;
    private String arrivalCityName;

    private Long departureCityId;
    private Long arrivalCityId;
    @NotBlank(message = "La aerolínea es obligatoria")
    @Size(max = 100)
    private String airline;

    @Size(max = 255)
    @URL(message = "La URL del logo debe ser válida")
    private String airlineLogoUrl;

    @NotBlank(message = "El número de vuelo es obligatorio")
    @Size(max = 20)
    private String flightNumber;

    @NotNull(message = "La fecha/hora de salida es obligatoria")
    @FutureOrPresent(message = "La fecha/hora de salida debe ser en el presente o futuro")
    private LocalDateTime departureDateTime;

    @NotNull(message = "La fecha/hora de llegada es obligatoria")
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

    @NotNull
    private FlightClass flightClass;

    public void validate() {
        if (departureCityId != null && departureCityId.equals(arrivalCityId)) {
            throw new IllegalArgumentException("La ciudad de origen y destino no pueden ser iguales");
        }
        if (arrivalDateTime != null && !arrivalDateTime.isAfter(departureDateTime)) {
            throw new IllegalArgumentException("La fecha/hora de llegada debe ser posterior a la de salida");
        }
    }

    public FlightDTO(){}

    public FlightDTO(Long flightId, String departureCityName, String arrivalCityName, Long departureCityId, Long arrivalCityId, String airline, String airlineLogoUrl, String flightNumber, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Integer durationMinutes, Double price, Integer adults, Integer children, FlightClass flightClass) {
        this.flightId = flightId;
        this.departureCityName = departureCityName;
        this.arrivalCityName = arrivalCityName;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.airline = airline;
        this.airlineLogoUrl = airlineLogoUrl;
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.adults = adults;
        this.children = children;
        this.flightClass = flightClass;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(Long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public Long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(Long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
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
}