package com.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Project;
import com.entities.ProjectStatus;
import com.entities.ProjectType;
import com.entities.Ward;
import com.service.ProjectService;
import com.service.WardService;

@Controller
@RequestMapping("/map")
public class MapController {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private WardService wardService;
    @GetMapping
    public String mapView(Model model) {
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("projectStatuses", ProjectStatus.values());

        List<Ward> wards = wardService.findAllOrdered();
        model.addAttribute("wards", wards);
        model.addAttribute("wards", wards);
        
        return "map";
    }
    
    @GetMapping("/api/projects")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getProjectsForMap(
            @RequestParam(required = false) Double swLat,
            @RequestParam(required = false) Double swLng,
            @RequestParam(required = false) Double neLat,
            @RequestParam(required = false) Double neLng,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String ward,
            @RequestParam(required = false) String status) {
        
        List<Project> projects;
        
        if (swLat != null && swLng != null && neLat != null && neLng != null) {
            projects = projectService.findProjectsInBounds(
                BigDecimal.valueOf(swLat), BigDecimal.valueOf(swLng),
                BigDecimal.valueOf(neLat), BigDecimal.valueOf(neLng)
            );
        } else {
            projects = projectService.findActiveProjects();
        }
        
        // Apply filters
        if (type != null && !type.isEmpty()) {
            ProjectType projectType = ProjectType.valueOf(type);
            projects = projects.stream()
                    .filter(p -> p.getProjectType() == projectType)
                    .toList();
        }
        
        if (ward != null && !ward.isEmpty()) {
            projects = projects.stream()
                    .filter(p -> p.getWard().equals(ward))
                    .toList();
        }
        
        if (status != null && !status.isEmpty()) {
            ProjectStatus projectStatus = ProjectStatus.valueOf(status);
            projects = projects.stream()
                    .filter(p -> p.getStatus() == projectStatus)
                    .toList();
        }
        
        // Convert to map format for JSON response
        List<Map<String, Object>> mapData = projects.stream()
                .map(this::convertProjectToMapData)
                .toList();
        
        return ResponseEntity.ok(mapData);
    }
    
    @GetMapping("/api/project/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getProjectDetails(@PathVariable Integer id) {
        return projectService.findById(id)
                .map(project -> {
                    projectService.incrementViewCount(id);
                    return ResponseEntity.ok(convertProjectToDetailData(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    private Map<String, Object> convertProjectToMapData(Project project) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("slug", project.getSlug());
        data.put("address", project.getAddress());
        data.put("ward", project.getWard());
        data.put("latitude", project.getLatitude());
        data.put("longitude", project.getLongitude());
        data.put("projectType", project.getProjectType().name());
        data.put("projectTypeDisplay", project.getProjectType().getDisplayName());
        data.put("status", project.getStatus().name());
        data.put("statusDisplay", project.getStatus().getDisplayName());
        data.put("priceFrom", project.getPriceFrom());
        data.put("priceTo", project.getPriceTo());
        data.put("areaFrom", project.getAreaFrom());
        data.put("areaTo", project.getAreaTo());
        data.put("thumbnail", project.getThumbnail());
        data.put("isFeatured", project.getIsFeatured());
        data.put("isHot", project.getIsHot());
        data.put("totalUnits", project.getTotalUnits());
        data.put("availableUnits", project.getAvailableUnits());
        
        if (project.getDeveloper() != null) {
            data.put("developerName", project.getDeveloper().getName());
        }
        
        return data;
    }
    
    private Map<String, Object> convertProjectToDetailData(Project project) {
        Map<String, Object> data = convertProjectToMapData(project);
        data.put("description", project.getDescription());
        data.put("amenities", project.getAmenities());
        data.put("completionYear", project.getCompletionYear());
        data.put("handoverYear", project.getHandoverYear());
        data.put("videoUrl", project.getVideoUrl());
        data.put("virtualTourUrl", project.getVirtualTourUrl());
        data.put("viewCount", project.getViewCount());
        
        if (project.getDeveloper() != null) {
            Map<String, Object> developerData = new HashMap<>();
            developerData.put("name", project.getDeveloper().getName());
            developerData.put("phone", project.getDeveloper().getPhone());
            developerData.put("email", project.getDeveloper().getEmail());
            developerData.put("website", project.getDeveloper().getWebsite());
            data.put("developer", developerData);
        }
        
        return data;
    }
}
