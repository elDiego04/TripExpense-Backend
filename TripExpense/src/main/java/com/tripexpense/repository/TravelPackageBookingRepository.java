package com.tripexpense.repository;

import com.tripexpense.entity.TravelPackageBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TravelPackageBookingRepository extends JpaRepository<TravelPackageBooking, Long> {
    List<TravelPackageBooking> findByUserId(Long userId);
}
