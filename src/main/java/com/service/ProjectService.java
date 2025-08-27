package com.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.entities.Project;
import com.entities.ProjectImage;
import com.entities.ProjectStatus;
import com.entities.ProjectType;

public interface ProjectService {
	Project createProject(Project project);
    Project updateProject(Project project);
    Optional<Project> findById(Integer id);
    Optional<Project> findBySlug(String slug);
    List<Project> findAll();
    List<Project> findActiveProjects();
    List<Project> findFeaturedProjects();
    List<Project> findHotProjects();
    List<Project> findByWard(String ward);
    List<Project> findByProjectType(ProjectType projectType);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByDeveloperId(Integer developerId);
    List<ProjectImage> findImagesByProjectId(Integer projectId);
    // Search methods
    List<Project> searchProjects(String keyword);
    List<Project> searchByFilters(ProjectType projectType, String ward, 
                                  BigDecimal minPrice, BigDecimal maxPrice,
                                  BigDecimal minArea, BigDecimal maxArea,
                                  ProjectStatus status);
    
    // Map methods
    List<Project> findProjectsInBounds(BigDecimal swLat, BigDecimal swLng, 
                                      BigDecimal neLat, BigDecimal neLng);
    List<Project> findNearbyProjects(BigDecimal latitude, BigDecimal longitude, Double radiusKm);
    
    void deleteProject(Integer id);
    void activateProject(Integer id);
    void deactivateProject(Integer id);
    void incrementViewCount(Integer id);
    String generateSlug(String name);
    
    // Statistics
    Long getTotalActiveProjects();
    Long getProjectCountByType(ProjectType projectType);
    Long getProjectCountByWard(String ward);
}
