package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.entities.Project;
import com.entities.ProjectImage;
import com.entities.ProjectStatus;
import com.entities.ProjectType;
import com.repository.ProjectRepository;
import com.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Override
    public Project createProject(Project project) {
        // Generate slug if not provided
        if (project.getSlug() == null || project.getSlug().trim().isEmpty()) {
            project.setSlug(generateSlug(project.getName()));
        }
        
        // Ensure slug is unique
        String originalSlug = project.getSlug();
        int counter = 1;
        while (projectRepository.findBySlug(project.getSlug()).isPresent()) {
            project.setSlug(originalSlug + "-" + counter);
            counter++;
        }
        
        project.setIsActive(true);
        project.setViewCount(0);
        
        return projectRepository.save(project);
    }
    
    @Override
    public Project updateProject(Project project) {
        Optional<Project> existing = projectRepository.findById(project.getId());
        if (existing.isEmpty()) {
            throw new RuntimeException("Project not found");
        }
        
        // Update slug if name changed
        Project existingProject = existing.get();
        if (!existingProject.getName().equals(project.getName())) {
            String newSlug = generateSlug(project.getName());
            if (!newSlug.equals(project.getSlug())) {
                // Check if new slug is unique
                Optional<Project> slugCheck = projectRepository.findBySlug(newSlug);
                if (slugCheck.isEmpty() || slugCheck.get().getId().equals(project.getId())) {
                    project.setSlug(newSlug);
                }
            }
        }
        
        return projectRepository.save(project);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Integer id) {
        return projectRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findBySlug(String slug) {
        return projectRepository.findBySlug(slug);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findActiveProjects() {
        return projectRepository.findByIsActive(true);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findFeaturedProjects() {
        return projectRepository.findByIsFeatured(true);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findHotProjects() {
        return projectRepository.findByIsHot(true);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByWard(String ward) {
        return projectRepository.findByWard(ward);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByProjectType(ProjectType projectType) {
        return projectRepository.findByProjectType(projectType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByDeveloperId(Integer developerId) {
        return projectRepository.findByDeveloperId(developerId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> searchProjects(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findActiveProjects();
        }
        
        List<Project> byName = projectRepository.findByNameContaining(keyword);
        List<Project> byAddress = projectRepository.findByAddressContaining(keyword);
        
        // Combine and remove duplicates
        return Stream.concat(byName.stream(), byAddress.stream())
                .distinct()
                .collect(Collectors.toList());

    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> searchByFilters(ProjectType projectType, String ward, 
                                        BigDecimal minPrice, BigDecimal maxPrice,
                                        BigDecimal minArea, BigDecimal maxArea,
                                        ProjectStatus status) {
        
        List<Project> results = findActiveProjects();
        
        if (projectType != null) {
            results = results.stream()
                    .filter(p -> p.getProjectType() == projectType)
                    .collect(Collectors.toList());
        }
        
        if (ward != null && !ward.trim().isEmpty()) {
            results = results.stream()
                    .filter(p -> p.getWard().equals(ward))
                    .collect(Collectors.toList());
        }
        
        if (status != null) {
            results = results.stream()
                    .filter(p -> p.getStatus() == status)
                    .collect(Collectors.toList());
        }
        
        if (minPrice != null) {
            results = results.stream()
                    .filter(p -> p.getPriceFrom() == null || p.getPriceFrom().compareTo(minPrice) >= 0)
                    .collect(Collectors.toList());
        }
        
        if (maxPrice != null) {
            results = results.stream()
                    .filter(p -> p.getPriceTo() == null || p.getPriceTo().compareTo(maxPrice) <= 0)
                    .collect(Collectors.toList());
        }
        
        if (minArea != null) {
            results = results.stream()
                    .filter(p -> p.getAreaFrom() == null || p.getAreaFrom().compareTo(minArea) >= 0)
                    .collect(Collectors.toList());
        }
        
        if (maxArea != null) {
            results = results.stream()
                    .filter(p -> p.getAreaTo() == null || p.getAreaTo().compareTo(maxArea) <= 0)
                    .collect(Collectors.toList());
        }
        
        return results;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findProjectsInBounds(BigDecimal swLat, BigDecimal swLng, 
                                             BigDecimal neLat, BigDecimal neLng) {
        return projectRepository.findProjectsInBounds(swLat, swLng, neLat, neLng);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findNearbyProjects(BigDecimal latitude, BigDecimal longitude, Double radiusKm) {
        return projectRepository.findNearbyProjects(latitude, longitude, radiusKm);
    }
    
    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
    
    @Override
    public void activateProject(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            project.get().setIsActive(true);
            projectRepository.save(project.get());
        }
    }
    
    @Override
    public void deactivateProject(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            project.get().setIsActive(false);
            projectRepository.save(project.get());
        }
    }
    
    @Override
    public void incrementViewCount(Integer id) {
        projectRepository.incrementViewCount(id);
    }
    
    @Override
    public String generateSlug(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "project";
        }
        
        // Remove Vietnamese accents
        String slug = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
        
        return slug.isEmpty() ? "project" : slug;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long getTotalActiveProjects() {
        return (long) findActiveProjects().size();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long getProjectCountByType(ProjectType projectType) {
        return projectRepository.countByProjectType(projectType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long getProjectCountByWard(String ward) {
        return projectRepository.countByWard(ward);
    }

	@Override
	public List<ProjectImage> findImagesByProjectId(Integer projectId) {
		return projectRepository.findImagesByProjectId(projectId);
	}
}