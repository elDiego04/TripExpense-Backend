package com.tripexpense.dto;

import com.tripexpense.enums.TravelPlanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class TravelPlanDTO {

    private Long travelPlanId;

    @NotNull
    private UserDTO user;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

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

    @PositiveOrZero
    private Double totalCost;

    @Enumerated(EnumType.STRING)
    private TravelPlanStatus status;

    @NotNull
    private CityDTO destinationCity;


    private FlightDTO flight;


    private HotelDTO hotel;

    private List<ActivityDTO> activities;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public TravelPlanDTO(){}

    public TravelPlanDTO(Long travelPlanId, UserDTO user, String title, String description, LocalDate departureDate, LocalDate returnDate, Integer adults, Integer children, Double totalCost, TravelPlanStatus status, CityDTO destinationCity, FlightDTO flight, HotelDTO hotel, List<ActivityDTO> activities, String notes) {
        this.travelPlanId = travelPlanId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
        this.children = children;
        this.totalCost = totalCost;
        this.status = status;
        this.destinationCity = destinationCity;
        this.flight = flight;
        this.hotel = hotel;
        this.activities = activities;
        this.notes = notes;
    }

    public Long getTravelPlanId() {
        return travelPlanId;
    }

    public void setTravelPlanId(Long travelPlanId) {
        this.travelPlanId = travelPlanId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public TravelPlanStatus getStatus() {
        return status;
    }

    public void setStatus(TravelPlanStatus status) {
        this.status = status;
    }

    public CityDTO getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(CityDTO destinationCity) {
        this.destinationCity = destinationCity;
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
