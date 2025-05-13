package com.tripexpense.service.impl;

import com.tripexpense.dto.HotelDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.Hotel;
import com.tripexpense.exception.DuplicateResourceException;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.HotelRepository;
import com.tripexpense.service.interfac.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        if (hotelRepository.existsByName(hotelDTO.getName())) {
            throw new DuplicateResourceException("Ya existe un hotel con este nombre");
        }

        City city = cityRepository.findById(hotelDTO.getCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad no encontrada"));

        Hotel hotel = new Hotel();
        mapDTOToEntity(hotelDTO, hotel);
        hotel.setCity(city);

        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToFullDTO(savedHotel);
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel no encontrado con ID: " + id));
        return convertToFullDTO(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> getHotelsByCity(Long cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new NoSuchElementException("Ciudad no encontrada con ID: " + cityId);
        }

        return hotelRepository.findByCityCityId(cityId).stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel no encontrado con ID: " + id));

        if (!existingHotel.getName().equals(hotelDTO.getName())) {
            if (hotelRepository.existsByName(hotelDTO.getName())) {
                throw new DuplicateResourceException("Ya existe un hotel con este nombre");
            }
        }

        if (hotelDTO.getCityId() != null &&
                !hotelDTO.getCityId().equals(existingHotel.getCity().getCityId())) {
            City newCity = cityRepository.findById(hotelDTO.getCityId())
                    .orElseThrow(() -> new NoSuchElementException("Ciudad no encontrada"));
            existingHotel.setCity(newCity);
        }

        mapDTOToEntity(hotelDTO, existingHotel);

        Hotel updatedHotel = hotelRepository.save(existingHotel);
        return convertToFullDTO(updatedHotel);
    }

    @Override
    @Transactional
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new NoSuchElementException("Hotel no encontrado con ID: " + id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> searchHotels(String query) {
        return hotelRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    private void mapDTOToEntity(HotelDTO dto, Hotel entity) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getImageUrl() != null) entity.setImageUrl(dto.getImageUrl());
        if (dto.getStars() != null) entity.setStars(dto.getStars());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getAmenities() != null) entity.setAmenities(new ArrayList<>(dto.getAmenities()));
        if (dto.getCheckInTime() != null) entity.setCheckInTime(dto.getCheckInTime());
        if (dto.getCheckOutTime() != null) entity.setCheckOutTime(dto.getCheckOutTime());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
    }

    private HotelDTO convertToFullDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setHotelId(hotel.getHotelId());
        dto.setCityId(hotel.getCity().getCityId());
        dto.setCityName(hotel.getCity().getName());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setImageUrl(hotel.getImageUrl());
        dto.setStars(hotel.getStars());
        dto.setDescription(hotel.getDescription());
        dto.setAmenities(new ArrayList<>(hotel.getAmenities()));
        dto.setCheckInTime(hotel.getCheckInTime());
        dto.setCheckOutTime(hotel.getCheckOutTime());
        dto.setEmail(hotel.getEmail());
        dto.setPhone(hotel.getPhone());
        return dto;
    }
}
