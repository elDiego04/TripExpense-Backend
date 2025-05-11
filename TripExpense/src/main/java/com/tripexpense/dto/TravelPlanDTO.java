package com.tripexpense.dto;

import com.tripexpense.entity.*;
import com.tripexpense.enums.PlanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class TravelPlanDTO {

    private User user;
    private City destination;
    private Flight flight;
    private Hotel hotel;
    private List<Activity> activities;


    private Long travelPlanId;
    private Long userId;
    private Long destinationCityId;
    private Long flightId;
    private Long hotelId;
    private List<Long> activityIds;


    @NotBlank
    @Size(max = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @Future
    private LocalDate endDate;

    @NotNull
    @Min(1)
    private Integer adults;

    @NotNull
    @Min(0)
    private Integer children;

    @PositiveOrZero
    private Double totalCost;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (endDate != null && !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("La fecha de regreso debe ser posterior a la fecha de salida");
        }
    }

    public TravelPlanDTO(){}

    public TravelPlanDTO(User user, City destination, Flight flight, Hotel hotel, List<Activity> activities, Long travelPlanId, Long userId, Long destinationCityId, Long flightId, Long hotelId, List<Long> activityIds, String title, String description, LocalDate startDate, LocalDate endDate, Integer adults, Integer children, Double totalCost, PlanStatus status, String notes) {
        this.user = user;
        this.destination = destination;
        this.flight = flight;
        this.hotel = hotel;
        this.activities = activities;
        this.travelPlanId = travelPlanId;
        this.userId = userId;
        this.destinationCityId = destinationCityId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.activityIds = activityIds;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adults = adults;
        this.children = children;
        this.totalCost = totalCost;
        this.status = status;
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Long getTravelPlanId() {
        return travelPlanId;
    }

    public void setTravelPlanId(Long travelPlanId) {
        this.travelPlanId = travelPlanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Long> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<Long> activityIds) {
        this.activityIds = activityIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public PlanStatus getStatus() {
        return status;
    }

    public void setStatus(PlanStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
