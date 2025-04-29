package com.tripexpense.service.impl;

import com.tripexpense.dto.HotelDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.Hotel;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.HotelRepository;
import com.tripexpense.service.interfac.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        if (hotelRepository.existsByName(hotelDTO.getName())) {
            throw new RuntimeException("Hotel name already exists");
        }

        City city = cityRepository.findById(hotelDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setCity(city);
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setAmenities(new ArrayList<>(hotelDTO.getAmenities()));
        hotel.setRoomTypes(new ArrayList<>(hotelDTO.getRoomTypes()));
        hotel.setPricePerNight(hotelDTO.getPricePerNight());
        hotel.setMaxAdults(hotelDTO.getMaxAdults());
        hotel.setMaxChildren(hotelDTO.getMaxChildren());
        hotel.setCheckInTime(hotelDTO.getCheckInTime());
        hotel.setCheckOutTime(hotelDTO.getCheckOutTime());
        hotel.setContactEmail(hotelDTO.getContactEmail());
        hotel.setContactPhone(hotelDTO.getContactPhone());

        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToDTO(savedHotel);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        return convertToDTO(hotel);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelsByCity(Long cityId) {
        return hotelRepository.findByCityCityId(cityId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        if (hotelDTO.getName() != null) existingHotel.setName(hotelDTO.getName());
        if (hotelDTO.getAddress() != null) existingHotel.setAddress(hotelDTO.getAddress());
        if (hotelDTO.getStarRating() != null) existingHotel.setStarRating(hotelDTO.getStarRating());
        if (hotelDTO.getDescription() != null) existingHotel.setDescription(hotelDTO.getDescription());
        if (hotelDTO.getAmenities() != null) existingHotel.setAmenities(new ArrayList<>(hotelDTO.getAmenities()));
        if (hotelDTO.getRoomTypes() != null) existingHotel.setRoomTypes(new ArrayList<>(hotelDTO.getRoomTypes()));
        if (hotelDTO.getPricePerNight() != null) existingHotel.setPricePerNight(hotelDTO.getPricePerNight());
        if (hotelDTO.getMaxAdults() != null) existingHotel.setMaxAdults(hotelDTO.getMaxAdults());
        if (hotelDTO.getMaxChildren() != null) existingHotel.setMaxChildren(hotelDTO.getMaxChildren());
        if (hotelDTO.getCheckInTime() != null) existingHotel.setCheckInTime(hotelDTO.getCheckInTime());
        if (hotelDTO.getCheckOutTime() != null) existingHotel.setCheckOutTime(hotelDTO.getCheckOutTime());
        if (hotelDTO.getContactEmail() != null) existingHotel.setContactEmail(hotelDTO.getContactEmail());
        if (hotelDTO.getContactPhone() != null) existingHotel.setContactPhone(hotelDTO.getContactPhone());

        if (hotelDTO.getCityId() != null && !hotelDTO.getCityId().equals(existingHotel.getCity().getCityId())) {
            City newCity = cityRepository.findById(hotelDTO.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            existingHotel.setCity(newCity);
        }

        Hotel updatedHotel = hotelRepository.save(existingHotel);
        return convertToDTO(updatedHotel);
    }

    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel not found with id: " + id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    public List<HotelDTO> searchHotels(String query) {
        return hotelRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HotelDTO convertToDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setHotelId(hotel.getHotelId());
        dto.setCityId(hotel.getCity().getCityId());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setStarRating(hotel.getStarRating());
        dto.setDescription(hotel.getDescription());
        dto.setAmenities(new ArrayList<>(hotel.getAmenities()));
        dto.setRoomTypes(new ArrayList<>(hotel.getRoomTypes()));
        dto.setPricePerNight(hotel.getPricePerNight());
        dto.setMaxAdults(hotel.getMaxAdults());
        dto.setMaxChildren(hotel.getMaxChildren());
        dto.setCheckInTime(hotel.getCheckInTime());
        dto.setCheckOutTime(hotel.getCheckOutTime());
        dto.setContactEmail(hotel.getContactEmail());
        dto.setContactPhone(hotel.getContactPhone());
        return dto;
    }
}