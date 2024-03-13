package com.tour.repository;

import com.tour.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, String> {

    Optional<Booking> findTopByOrderByIdDesc();

    Optional<List<Booking>> findByCustId(String string);

    Optional<List<Booking>> findByTripId(String id);

    Optional<List<Booking>> findByBookingStatusNot(String requested);

    Optional<List<Booking>> findByTripIdAndBookingStatusNot(String id, String status);
}
