package com.repository;

import java.util.List;
import java.util.Optional;

import com.entities.Amenity;
import com.entities.AmenityCategory;

public interface AmenityRepository {
    Amenity save(Amenity amenity);
    Optional<Amenity> findById(Integer id);
    List<Amenity> findAll();
    List<Amenity> findByCategory(AmenityCategory category);
    List<Amenity> findByNameContaining(String name);
    void deleteById(Integer id);
}
