package com.tripexpense.entity;

import com.tripexpense.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotel_bookings")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ElementCollection(targetClass = RoomType.class)
    @CollectionTable(name = "booking_room_types", joinColumns = @JoinColumn(name = "booking_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private List<RoomType> roomTypes;

    @NotNull
    @PositiveOrZero
    private Double pricePerNight;

    @NotNull
    @Min(1)
    private Integer maxAdults;

    @NotNull
    @Min(0)
    private Integer maxChildren;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public HotelBooking(){}

    public HotelBooking(Long id, Hotel hotel, List<RoomType> roomTypes, Double pricePerNight, Integer maxAdults, Integer maxChildren, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.hotel = hotel;
        this.roomTypes = roomTypes;
        this.pricePerNight = pricePerNight;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
