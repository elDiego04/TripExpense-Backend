package com.tripexpense.service.interfac;

import com.tripexpense.dto.PopularSearchDTO;
import com.tripexpense.dto.SearchQueryDTO;

import java.util.List;

public interface SearchQueryService {
    SearchQueryDTO saveSearchQuery(SearchQueryDTO searchQueryDTO);
    SearchQueryDTO getSearchQueryById(Long id);
    List<SearchQueryDTO> getAllSearchQueries();
    List<SearchQueryDTO> getSearchQueriesByUser(Long userId);
    List<SearchQueryDTO> getRecentSearchQueries(int days);
    void deleteSearchQuery(Long id);
    List<PopularSearchDTO> getPopularDestinations();
}
