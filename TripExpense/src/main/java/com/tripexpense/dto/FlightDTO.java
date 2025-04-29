package com.tripexpense.dto;

import com.tripexpense.enums.FlightClass;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDTO {
    private Long flightId;
    private String airline;
    private String flightNumber;
    private Long departureCityId;
    private String departureCityName;
    private Long arrivalCityId;
    private String arrivalCityName;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer duration; // in minutes
    private Double price;
    private Integer adultNumber;
    private Integer childNumber;
    private FlightClass flightClass;
    
    public FlightDTO(){}

    public FlightDTO(Long flightId, String airline, String flightNumber, Long departureCityId, String departureCityName, Long arrivalCityId, String arrivalCityName, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, Integer duration, Double price, Integer adultNumber, Integer childNumber, FlightClass flightClass) {
        this.flightId = flightId;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureCityId = departureCityId;
        this.departureCityName = departureCityName;
        this.arrivalCityId = arrivalCityId;
        this.arrivalCityName = arrivalCityName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.flightClass = flightClass;
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

    public Long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(Long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public Long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(Long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
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
}
