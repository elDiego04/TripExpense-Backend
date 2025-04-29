package com.tripexpense.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchQueryDTO {

    private Long id;
    private Long userId;
    private Long originCityId;
    private String originCityName;
    private Long destinationCityId;
    private String destinationCityName;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer adults;
    private Integer children;
    private LocalDateTime searchDate;

    public SearchQueryDTO(){}

    public SearchQueryDTO(Long id, Long userId, Long originCityId, String originCityName, Long destinationCityId, String destinationCityName, LocalDate departureDate, LocalDate returnDate, Integer adults, Integer children, LocalDateTime searchDate) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
