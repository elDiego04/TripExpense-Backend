package com.tripexpense.service.interfac;

import com.tripexpense.dto.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO createCity(CityDTO cityDTO);
    CityDTO getCityById(Long id);
    CityDTO getCityByName(String name);
    List<CityDTO> getAllCities();
    CityDTO updateCity(Long id, CityDTO cityDTO);
    void deleteCity(Long id);
}
