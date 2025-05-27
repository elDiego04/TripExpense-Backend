package com.tripexpense.repository;

import com.tripexpense.entity.ActivityBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityBookingRepository extends JpaRepository<ActivityBooking, Long> {
    List<ActivityBooking> findByUser_Id(Long userId);
}
