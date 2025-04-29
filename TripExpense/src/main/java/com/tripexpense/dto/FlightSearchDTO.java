package com.tripexpense.dto;

import java.time.LocalDate;

public class FlightSearchDTO {
    private Long departureCityId;
    private Long arrivalCityId;
    private LocalDate departureDate;
    private Integer adults;
    private Integer children;

    public FlightSearchDTO(){}

    public FlightSearchDTO(Long departureCityId, Long arrivalCityId, LocalDate departureDate, Integer adults, Integer children) {
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.departureDate = departureDate;
        this.adults = adults;
        this.children = children;
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
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
}
