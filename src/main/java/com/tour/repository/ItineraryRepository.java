package com.tour.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tour.model.Itinerary;

@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary, String>{

	Optional<List<Itinerary>> findByPackageId(String packid);

	Optional<Itinerary> findTopByOrderByIdDesc();
	

}
