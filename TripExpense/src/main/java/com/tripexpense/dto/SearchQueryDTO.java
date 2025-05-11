package com.tripexpense.dto;

import com.tripexpense.entity.City;
import com.tripexpense.entity.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchQueryDTO {
    private City originCity;
    private City destinationCity;
    private User user;
    private Long searchId;
    private Long userId;
    private Long originCityId;
    private String originCityName;
    private Long destinationCityId;
    private String destinationCityName;

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

    public void validate() {
        if (returnDate != null && !returnDate.isAfter(departureDate)) {
            throw new IllegalArgumentException("La fecha de regreso debe ser posterior a la fecha de salida");
        }
    }

    public SearchQueryDTO() {}

    public SearchQueryDTO(City originCity, City destinationCity, User user, Long searchId, Long userId, Long originCityId, String originCityName, Long destinationCityId, String destinationCityName, LocalDate departureDate, LocalDate returnDate, Integer adults, Integer children, LocalDateTime searchDate) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.user = user;
        this.searchId = searchId;
        this.userId = userId;
        this.originCityId = originCityId;
        this.originCityName = originCityName;
        this.destinationCityId = destinationCityId;
        this.destinationCityName = destinationCityName;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
        this.children = children;
        this.searchDate = searchDate;
    }

    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(Long originCityId) {
        this.originCityId = originCityId;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public void setOriginCityName(String originCityName) {
        this.originCityName = originCityName;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
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
}