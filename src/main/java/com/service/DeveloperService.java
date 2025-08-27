package com.service;

import java.util.List;
import java.util.Optional;

import com.entities.Developer;

public interface DeveloperService {
	 Developer createDeveloper(Developer developer);
	    Developer updateDeveloper(Developer developer);
	    Optional<Developer> findById(Integer id);
	    List<Developer> findAll();
	    List<Developer> findActiveDevelopers();
	    List<Developer> searchByName(String name);
	    void deleteDeveloper(Integer id);
	    void activateDeveloper(Integer id);
	    void deactivateDeveloper(Integer id);
}
