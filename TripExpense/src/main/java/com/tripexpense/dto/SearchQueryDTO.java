package com.tripexpense.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchQueryDTO {

    private Long searchId;

    @NotNull
    private CityDTO originCity;

    @NotNull
    private CityDTO destinationCity;

    @NotNull
    @FutureOrPresent
    private LocalDate departureDate;

    @Future
    private LocalDate returnDate;

    @NotNull
    @Min(1)
    private Integer adults;

    @NotNull
    @Min(0)
    private Integer children;

    private LocalDateTime searchDate;

    private UserDTO user;

    public SearchQueryDTO(){}

    public SearchQueryDTO(Long searchId, CityDTO originCity, CityDTO destinationCity, LocalDate departureDate, LocalDate returnDate, Integer adults, Integer children, LocalDateTime searchDate, UserDTO user) {
        this.searchId = searchId;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
        this.children = children;
        this.searchDate = searchDate;
        this.user = user;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public CityDTO getOriginCity() {
        return originCity;
    }

    public void setOriginCity(CityDTO originCity) {
        this.originCity = originCity;
    }

    public CityDTO getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(CityDTO destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
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

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
