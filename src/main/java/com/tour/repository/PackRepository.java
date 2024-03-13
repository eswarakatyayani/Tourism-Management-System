package com.tour.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tour.model.Pack;

@Repository
public interface PackRepository extends CrudRepository<Pack, String>{

	Optional<Pack> findTopByOrderByIdDesc();

	Optional<Pack> findByPackageId(String packid);

}
