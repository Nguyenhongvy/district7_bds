package com.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.entities.Unit;
import com.entities.UnitStatus;

public interface UnitRepository {
	Unit save(Unit unit);
    Optional<Unit> findById(Integer id);
    List<Unit> findAll();
    List<Unit> findByProjectId(Integer projectId);
    List<Unit> findByProjectIdAndStatus(Integer projectId, UnitStatus status);
    List<Unit> findByStatus(UnitStatus status);
    Optional<Unit> findByProjectIdAndUnitCode(Integer projectId, String unitCode);
    
    // Search methods
    List<Unit> findByBedroomsAndBathrooms(Integer bedrooms, Integer bathrooms);
    List<Unit> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Unit> findByAreaRange(BigDecimal minArea, BigDecimal maxArea);
    List<Unit> findByFloorNumber(Integer floorNumber);
    List<Unit> findByTower(String tower);
    
    // Statistics
    Long countByProjectId(Integer projectId);
    Long countByProjectIdAndStatus(Integer projectId, UnitStatus status);
    
    void deleteById(Integer id);
    void deleteByProjectId(Integer projectId);
}
