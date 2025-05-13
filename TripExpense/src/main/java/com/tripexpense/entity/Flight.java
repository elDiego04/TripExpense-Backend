package com.tripexpense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Positive(message = "La duración debe ser positiva")
    private Integer durationMinutes;

    @OneToOne(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FlightBooking booking;

    public Flight() {}

    public Flight(Long flightId, String airline, String airlineLogoUrl, String flightNumber, City departureCity, City arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Integer durationMinutes, FlightBooking booking) {
        this.flightId = flightId;
        this.airline = airline;
        this.airlineLogoUrl = airlineLogoUrl;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.durationMinutes = durationMinutes;
        this.booking = booking;
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

    public FlightBooking getBooking() {
        return booking;
    }

    public void setBooking(FlightBooking booking) {
        this.booking = booking;
    }
}
