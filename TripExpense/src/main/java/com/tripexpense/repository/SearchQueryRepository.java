package com.tripexpense.repository;

import com.tripexpense.entity.SearchQuery;
import com.tripexpense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearchQueryRepository extends JpaRepository<SearchQuery, Long> {

    List<SearchQuery> findByUserOrderBySearchDate(User user);
    List<SearchQuery> findBySearchDateAfter(LocalDateTime date);

    List<SearchQuery> findByUserIdOrderBySearchDateDesc(Long userId);
}
