package com.service.impl;

import com.entities.Ward;
import com.repository.WardRepository;
import com.service.WardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class WardServiceImp implements WardService {
    
    @Autowired
    private WardRepository wardDAO;
    
    /**
     * Find all wards
     */
    @Override
    public List<Ward> findAll() {
        return wardDAO.findAll();
    }
    
    /**
     * Find all wards ordered by name
     */
    @Override
    public List<Ward> findAllOrdered() {
        return wardDAO.findAllOrderedByName();
    }
    
    /**
     * Find ward by ID
     */
    @Override
    public Ward findById(Integer id) {
        return wardDAO.findById(id);
    }
    
    /**
     * Find ward by name
     */
    @Override
    public Ward findByName(String name) {
        return wardDAO.findByName(name);
    }
    
    /**
     * Search wards by name containing
     */
    @Override
    public List<Ward> findByNameContaining(String name) {
        return wardDAO.findByNameContaining(name);
    }
    
    /**
     * Get all ward names for dropdown
     */
    @Override
    public List<String> getAllWardNames() {
        return wardDAO.findAllWardNames();
    }
    
    /**
     * Save ward
     */
    @Override
    public Ward save(Ward ward) {
        return wardDAO.save(ward);
    }
    
    /**
     * Update ward
     */
    @Override
    public Ward update(Integer id, Ward wardDetails) {
        Ward ward = wardDAO.findById(id);
        if (ward != null) {
            ward.setName(wardDetails.getName());
            ward.setDescription(wardDetails.getDescription());
            ward.setLatitude(wardDetails.getLatitude());
            ward.setLongitude(wardDetails.getLongitude());
            ward.setPopulation(wardDetails.getPopulation());
            ward.setAreaSqkm(wardDetails.getAreaSqkm());
            return wardDAO.update(ward);
        }
        throw new RuntimeException("Ward not found with id: " + id);
    }
    
    /**
     * Delete ward by ID
     */
    @Override
    public void deleteById(Integer id) {
        wardDAO.deleteById(id);
    }
    
    /**
     * Check if ward name exists
     */
    @Override
    public boolean existsByName(String name) {
        return wardDAO.existsByName(name);
    }
    
    /**
     * Count total wards
     */
    @Override
    public long countTotal() {
        return wardDAO.countTotal();
    }
    
    /**
     * Find wards by population range
     */
    @Override
    public List<Ward> findByPopulationRange(Integer min, Integer max) {
        return wardDAO.findByPopulationBetween(min, max);
    }
    
    /**
     * Find wards by area range
     */
    @Override
    public List<Ward> findByAreaRange(Double min, Double max) {
        return wardDAO.findByAreaBetween(min, max);
    }
    
    /**
     * Create new ward
     */
    @Override
    public Ward createWard(String name, String description, BigDecimal latitude, BigDecimal longitude) {
        Ward ward = new Ward(name, description, latitude, longitude);
        return wardDAO.save(ward);
    }
    
    /**
     * Initialize default wards for District 7 (if needed)
     */
    @Override
    public void initializeDistrict7Wards() {
        String[] wardNames = {
            "Phường Tân Thuận Đông", "Phường Tân Thuận Tây", "Phường Tân Kiểng",
            "Phường Tân Hưng", "Phường Bình Thuận", "Phường Tân Phong",
            "Phường Tân Phú", "Phường Tân Quy", "Phường Phú Thuận", "Phường Phú Mỹ"
        };
        
        for (String wardName : wardNames) {
            if (!existsByName(wardName)) {
                Ward ward = new Ward(wardName);
                save(ward);
            }
        }
    }
}