package com.tripexpense.service.impl;

import com.tripexpense.dto.SearchQueryDTO;
import com.tripexpense.entity.City;
import com.tripexpense.entity.SearchQuery;
import com.tripexpense.entity.User;
import com.tripexpense.repository.CityRepository;
import com.tripexpense.repository.SearchQueryRepository;
import com.tripexpense.repository.UserRepository;
import com.tripexpense.service.interfac.SearchQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRepository searchQueryRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public SearchQueryServiceImpl(SearchQueryRepository searchQueryRepository,
                                  UserRepository userRepository,
                                  CityRepository cityRepository) {
        this.searchQueryRepository = searchQueryRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public SearchQueryDTO saveSearchQuery(SearchQueryDTO searchQueryDTO) {
        searchQueryDTO.validate();

        User user = userRepository.findById(searchQueryDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        City originCity = cityRepository.findById(searchQueryDTO.getOriginCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad de origen no encontrada"));

        City destinationCity = cityRepository.findById(searchQueryDTO.getDestinationCityId())
                .orElseThrow(() -> new NoSuchElementException("Ciudad de destino no encontrada"));

        if (originCity.getCityId().equals(destinationCity.getCityId())) {
            throw new RuntimeException("La ciudad de origen y destino no pueden ser iguales");
        }

        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUser(user);
        searchQuery.setOriginCity(originCity);
        searchQuery.setDestinationCity(destinationCity);
        searchQuery.setDepartureDate(searchQueryDTO.getDepartureDate());
        searchQuery.setReturnDate(searchQueryDTO.getReturnDate());
        searchQuery.setAdults(searchQueryDTO.getAdults());
        searchQuery.setChildren(searchQueryDTO.getChildren());
        searchQuery.setSearchDate(LocalDateTime.now());

        SearchQuery savedQuery = searchQueryRepository.save(searchQuery);
        return convertToFullDTO(savedQuery);
    }

    @Override
    @Transactional(readOnly = true)
    public SearchQueryDTO getSearchQueryById(Long id) {
        SearchQuery searchQuery = searchQueryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Búsqueda no encontrada con ID: " + id));
        return convertToFullDTO(searchQuery);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchQueryDTO> getAllSearchQueries() {
        return searchQueryRepository.findAll().stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchQueryDTO> getSearchQueriesByUser(Long userId) {
        return searchQueryRepository.findByUserIdOrderBySearchDateDesc(userId).stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchQueryDTO> getRecentSearchQueries(int days) {
        LocalDateTime dateThreshold = LocalDateTime.now().minusDays(days);
        return searchQueryRepository.findBySearchDateAfter(dateThreshold).stream()
                .map(this::convertToFullDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSearchQuery(Long id) {
        if (!searchQueryRepository.existsById(id)) {
            throw new NoSuchElementException("Búsqueda no encontrada con ID: " + id);
        }
        searchQueryRepository.deleteById(id);
    }

    private SearchQueryDTO convertToFullDTO(SearchQuery searchQuery) {
        SearchQueryDTO dto = new SearchQueryDTO();

        dto.setUser(searchQuery.getUser());
        dto.setOriginCity(searchQuery.getOriginCity());
        dto.setDestinationCity(searchQuery.getDestinationCity());

        dto.setSearchId(searchQuery.getSearchId());
        dto.setUserId(searchQuery.getUser().getId());
        dto.setOriginCityId(searchQuery.getOriginCity().getCityId());
        dto.setOriginCityName(searchQuery.getOriginCity().getName());
        dto.setDestinationCityId(searchQuery.getDestinationCity().getCityId());
        dto.setDestinationCityName(searchQuery.getDestinationCity().getName());

        dto.setDepartureDate(searchQuery.getDepartureDate());
        dto.setReturnDate(searchQuery.getReturnDate());
        dto.setAdults(searchQuery.getAdults());
        dto.setChildren(searchQuery.getChildren());
        dto.setSearchDate(searchQuery.getSearchDate());

        return dto;
    }
}