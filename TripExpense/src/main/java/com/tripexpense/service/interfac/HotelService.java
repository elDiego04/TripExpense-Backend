package com.tripexpense.service.interfac;

import com.tripexpense.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO getHotelById(Long id);
    List<HotelDTO> getAllHotels();
    List<HotelDTO> getHotelsByCity(Long cityId);
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);
    void deleteHotel(Long id);
    List<HotelDTO> searchHotels(String query);
  }
