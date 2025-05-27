package com.tripexpense.repository;

import com.tripexpense.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
    List<FlightBooking> findByUser_Id(Long userId);
}
