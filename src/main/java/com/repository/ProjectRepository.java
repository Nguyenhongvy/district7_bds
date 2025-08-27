package com.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.entities.Project;
import com.entities.ProjectImage;
import com.entities.ProjectStatus;
import com.entities.ProjectType;

public interface ProjectRepository {
	Project save(Project project);
    Optional<Project> findById(Integer id);
    Optional<Project> findBySlug(String slug);
    List<Project> findAll();
    List<Project> findByIsActive(Boolean isActive);
    List<Project> findByIsFeatured(Boolean isFeatured);
    List<Project> findByIsHot(Boolean isHot);
    List<Project> findByWard(String ward);
    List<Project> findByProjectType(ProjectType projectType);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByDeveloperId(Integer developerId);
    List<ProjectImage> findImagesByProjectId(Integer projectId);
    // Search methods
    List<Project> findByNameContaining(String name);
    List<Project> findByAddressContaining(String address);
    List<Project> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Project> findByAreaRange(BigDecimal minArea, BigDecimal maxArea);
    
    // Map-specific methods
    List<Project> findProjectsInBounds(BigDecimal swLat, BigDecimal swLng, BigDecimal neLat, BigDecimal neLng);
    List<Project> findNearbyProjects(BigDecimal latitude, BigDecimal longitude, Double radiusKm);
    
    // Statistics
    Long countByStatus(ProjectStatus status);
    Long countByProjectType(ProjectType projectType);
    Long countByWard(String ward);
    
    void deleteById(Integer id);
    void incrementViewCount(Integer projectId);
}
