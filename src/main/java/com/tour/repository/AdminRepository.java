package com.tour.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tour.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

	Optional<Admin> findByPassword(String password);

}
