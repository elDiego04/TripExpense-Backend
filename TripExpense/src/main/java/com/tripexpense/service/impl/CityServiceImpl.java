package com.tripexpense.service.impl;

import com.tripexpense.dto.CityDTO;
import com.tripexpense.entity.City;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.service.interfac.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        if (cityRepository.existsByNameAndCountry(cityDTO.getName(), cityDTO.getCountry())){
            throw new RuntimeException("La ciudad ya se encuentra registrada");
        }
        City city =new City();
        city.setName(cityDTO.getName());
        city.setCountry(cityDTO.getCountry());
        city.setDescription(cityDTO.getDescription());
        city.setLanguage(cityDTO.getLanguage());
        city.setCurrency(cityDTO.getCurrency());
        city.setClimate(cityDTO.getClimate());
        city.setBestTimeToVisit(cityDTO.getBestTimeToVisit());

        return convertToDTO(cityRepository.save(city));
    }

    @Override
    public CityDTO getCityById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()){
            return convertToDTO(city.get());
        } else {
            throw new RuntimeException("Ciudad no encontrada");
        }
    }

    @Override
    public CityDTO getCityByName(String name) {
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()){
            return convertToDTO(city.get());
        } else {
            throw new RuntimeException("Ciudad no encontrada");
        }
    }

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO updateCity(Long id, CityDTO cityDTO) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()){
            City city = cityOptional.get();
            city.setName(cityDTO.getName());
            city.setCountry(cityDTO.getCountry());
            city.setDescription(cityDTO.getDescription());
            city.setLanguage(cityDTO.getLanguage());
            city.setCurrency(cityDTO.getCurrency());
            city.setClimate(cityDTO.getClimate());
            city.setBestTimeToVisit(cityDTO.getBestTimeToVisit());

            return convertToDTO(cityRepository.save(city));
        } else {
            throw new RuntimeException("Ciudad no encontrada");
        }
    }

    @Override
    public void deleteCity(Long id) {
        if (cityRepository.existsById(id)){
            cityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ciudad no encontrada");
        }

    }

    private CityDTO convertToDTO(City city){
        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityId(city.getCityId());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setDescription(city.getDescription());
        cityDTO.setLanguage(city.getLanguage());
        cityDTO.setCurrency(city.getCurrency());
        cityDTO.setClimate(city.getClimate());
        cityDTO.setBestTimeToVisit(city.getBestTimeToVisit());

        return cityDTO;
    }
}
