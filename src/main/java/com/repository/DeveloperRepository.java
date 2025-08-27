package com.repository;

import java.util.List;
import java.util.Optional;

import com.entities.Developer;

public interface DeveloperRepository {
	Developer save(Developer developer);

	Optional<Developer> findById(Integer id);

	List<Developer> findAll();

	List<Developer> findByIsActive(Boolean isActive);

	void deleteById(Integer id);

	List<Developer> findByNameContaining(String name);
}
