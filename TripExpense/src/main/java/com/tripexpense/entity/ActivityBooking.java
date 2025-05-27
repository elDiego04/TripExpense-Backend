package com.tripexpense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_bookings")
public class ActivityBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityBookingId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Debe indicar el número de personas")
    @Min(value = 1, message = "Debe haber al menos una persona")
    private Integer numberOfPeople;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @Future(message = "La fecha de reserva debe ser en el futuro")
    private LocalDateTime bookingDate;

    @NotNull(message = "El precio total es obligatorio")
    @Positive(message = "El precio total debe ser mayor que cero")
    private Double totalPrice;

    public ActivityBooking() {}

    public ActivityBooking(Long bookingId, Activity activity, User user, Integer numberOfPeople, LocalDateTime bookingDate, Double totalPrice) {
        this.activityBookingId = bookingId;
        this.activity = activity;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
    }

    public Long getActivityBookingId() {
        return activityBookingId;
    }

    public void setActivityBookingId(Long activityBookingId) {
        this.activityBookingId = activityBookingId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
