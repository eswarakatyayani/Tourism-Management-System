package com.tour.repository;

import com.tour.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {


    Optional<Payment> findByBookingId(String bookingId);
}
