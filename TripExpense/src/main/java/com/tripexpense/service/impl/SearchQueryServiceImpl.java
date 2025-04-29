package com.tripexpense.service.impl;

import com.tripexpense.dto.PopularDestinationDTO;
import com.tripexpense.dto.PopularSearchDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    @Autowired
    private SearchQueryRepository searchQueryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public SearchQueryDTO saveSearchQuery(SearchQueryDTO searchQueryDTO) {
        User user = userRepository.findById(searchQueryDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        City originCity = cityRepository.findById(searchQueryDTO.getOriginCityId())
                .orElseThrow(() -> new RuntimeException("Origin city not found"));

        City destinationCity = cityRepository.findById(searchQueryDTO.getDestinationCityId())
                .orElseThrow(() -> new RuntimeException("Destination city not found"));

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
        return convertToDTO(savedQuery);
    }

    @Override
    public SearchQueryDTO getSearchQueryById(Long id) {
        SearchQuery searchQuery = searchQueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search query not found with id: " + id));
        return convertToDTO(searchQuery);
    }

    @Override
    public List<SearchQueryDTO> getAllSearchQueries() {
        return searchQueryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchQueryDTO> getSearchQueriesByUser(Long userId) {
        return searchQueryRepository.findByUserIdOrderBySearchDateDesc(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchQueryDTO> getRecentSearchQueries(int days) {
        LocalDateTime dateThreshold = LocalDateTime.now().minusDays(days);
        return searchQueryRepository.findBySearchDateAfter(dateThreshold).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSearchQuery(Long id) {
        if (!searchQueryRepository.existsById(id)) {
            throw new RuntimeException("Search query not found with id: " + id);
        }
        searchQueryRepository.deleteById(id);
    }

    private SearchQueryDTO convertToDTO(SearchQuery searchQuery) {
        SearchQueryDTO dto = new SearchQueryDTO();
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
