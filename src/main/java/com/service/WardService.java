package com.service;

import com.entities.Ward;
import java.math.BigDecimal;
import java.util.List;

public interface WardService {
    
    /**
     * Find all wards
     */
    List<Ward> findAll();
    
    /**
     * Find all wards ordered by name
     */
    List<Ward> findAllOrdered();
    
    /**
     * Find ward by ID
     */
    Ward findById(Integer id);
    
    /**
     * Find ward by name
     */
    Ward findByName(String name);
    
    /**
     * Search wards by name containing
     */
    List<Ward> findByNameContaining(String name);
    
    /**
     * Get all ward names for dropdown
     */
    List<String> getAllWardNames();
    
    /**
     * Save ward
     */
    Ward save(Ward ward);
    
    /**
     * Update ward
     */
    Ward update(Integer id, Ward wardDetails);
    
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
     * Find wards by population range
     */
    List<Ward> findByPopulationRange(Integer min, Integer max);
    
    /**
     * Find wards by area range
     */
    List<Ward> findByAreaRange(Double min, Double max);
    
    /**
     * Create new ward
     */
    Ward createWard(String name, String description, BigDecimal latitude, BigDecimal longitude);
    
    /**
     * Initialize default wards for District 7 (if needed)
     */
    void initializeDistrict7Wards();
}
