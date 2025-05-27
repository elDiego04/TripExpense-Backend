package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class FlightDTO {

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
    private Long departureCityId;

    @NotNull(message = "La ciudad de destino es obligatoria")
    private Long arrivalCityId;

    @NotNull(message = "La hora de salida es obligatoria")
    private LocalDateTime departureDateTime;

    @NotNull(message = "La hora de llegada es obligatoria")
    private LocalDateTime arrivalDateTime;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser positiva")
    private Integer durationMinutes;

    public FlightDTO() {}

    public FlightDTO(Long flightId, String airline, String airlineLogoUrl, String flightNumber,
                     Long departureCityId, Long arrivalCityId, LocalDateTime departureDateTime,
                     LocalDateTime arrivalDateTime, Integer durationMinutes) {
        this.flightId = flightId;
        this.airline = airline;
        this.airlineLogoUrl = airlineLogoUrl;
        this.flightNumber = flightNumber;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.durationMinutes = durationMinutes;
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
}