package com.tripexpense.dto;

public class PopularSearchDTO {
    private Long cityId;
    private String cityName;
    private Integer searchCount;

    public PopularSearchDTO(){}

    public PopularSearchDTO(Long cityId, String cityName, Integer searchCount) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.searchCount = searchCount;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }
}
