package com.tripexpense.dto;

import com.tripexpense.entity.City;

public class PopularDestinationDTO {
    private City destination;
    private Long count;

    public PopularDestinationDTO(City destination, Long count) {
        this.destination = destination;
        this.count = count;
    }

    public City getDestination() { return destination; }
    public Long getCount() { return count; }
}