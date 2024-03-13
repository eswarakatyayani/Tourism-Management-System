package com.tour.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tour.model.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, String>{

	Optional<List<Trip>> findByPackageId(String packid);

	Optional<Trip> findTopByOrderByIdDesc();

	

}
