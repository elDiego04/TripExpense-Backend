package com.tripexpense.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class ActivityBookingDTO {

    private Long id;

    @NotNull
    private Long activityId;

    private List<LocalDate> availabilityDates;

    @NotNull
    @Min(1)
    private Integer minPeople;

    @NotNull
    @Min(1)
    private Integer maxPeople;

    public ActivityBookingDTO() {}

    public ActivityBookingDTO(Long id, Long activityId, List<LocalDate> availabilityDates, Integer minPeople, Integer maxPeople) {
        this.id = id;
        this.activityId = activityId;
        this.availabilityDates = availabilityDates;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public List<LocalDate> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(List<LocalDate> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }

    public Integer getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }
}
