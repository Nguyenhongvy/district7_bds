package com.repository;

import java.util.List;

import com.entities.Ward;

public interface WardRepository {
	/**
     * Find all wards
     */
    List<Ward> findAll();
    
    /**
     * Find ward by ID
     */
    Ward findById(Integer id);
    
    /**
     * Find ward by name (exact match)
     */
    Ward findByName(String name);
    
    /**
     * Find ward by name containing (case insensitive)
     */
    List<Ward> findByNameContaining(String name);
    
    /**
     * Find all wards ordered by name
     */
    List<Ward> findAllOrderedByName();
    
    /**
     * Find wards within population range
     */
    List<Ward> findByPopulationBetween(Integer minPopulation, Integer maxPopulation);
    
    /**
     * Find wards within area range
     */
    List<Ward> findByAreaBetween(Double minArea, Double maxArea);
    
    /**
     * Save new ward
     */
    Ward save(Ward ward);
    
    /**
     * Update existing ward
     */
    Ward update(Ward ward);
    
    /**
     * Delete ward by ID
     */
    void deleteById(Integer id);
    
    /**
     * Check if ward name exists
     */
    boolean existsByName(String name);
    
    /**
     * Count total wards
     */
    long countTotal();
    
    /**
     * Get ward names only (for dropdown lists)
     */
    List<String> findAllWardNames();
}
