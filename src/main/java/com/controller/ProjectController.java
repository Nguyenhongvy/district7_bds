package com.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Project;
import com.entities.ProjectImage;
import com.entities.ProjectStatus;
import com.entities.ProjectType;
import com.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public String projectList(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String ward,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String minArea,
            @RequestParam(required = false) String maxArea,
            Model model) {
        
        List<Project> projects;
        
        if (search != null && !search.trim().isEmpty()) {
            projects = projectService.searchProjects(search);
        } else {
            // Apply filters
            ProjectType projectType = (type != null && !type.isEmpty()) ? ProjectType.valueOf(type) : null;
            ProjectStatus projectStatus = (status != null && !status.isEmpty()) ? ProjectStatus.valueOf(status) : null;
            BigDecimal minPriceDecimal = (minPrice != null && !minPrice.isEmpty()) ? new BigDecimal(minPrice) : null;
            BigDecimal maxPriceDecimal = (maxPrice != null && !maxPrice.isEmpty()) ? new BigDecimal(maxPrice) : null;
            BigDecimal minAreaDecimal = (minArea != null && !minArea.isEmpty()) ? new BigDecimal(minArea) : null;
            BigDecimal maxAreaDecimal = (maxArea != null && !maxArea.isEmpty()) ? new BigDecimal(maxArea) : null;
            
            projects = projectService.searchByFilters(
                projectType, ward, minPriceDecimal, maxPriceDecimal,
                minAreaDecimal, maxAreaDecimal, projectStatus
            );
        }
        
        model.addAttribute("projects", projects);
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("projectStatuses", ProjectStatus.values());
        
        // District 7 wards
        String[] wards = {
            "Phường Tân Thuận Đông", "Phường Tân Thuận Tây", "Phường Tân Kiểng",
            "Phường Tân Hưng", "Phường Bình Thuận", "Phường Tân Phong",
            "Phường Tân Phú", "Phường Tân Quy", "Phường Phú Thuận", "Phường Phú Mỹ"
        };
        model.addAttribute("wards", wards);
        
        // Keep filter values for form
        model.addAttribute("currentSearch", search);
        model.addAttribute("currentType", type);
        model.addAttribute("currentWard", ward);
        model.addAttribute("currentStatus", status);
        model.addAttribute("currentMinPrice", minPrice);
        model.addAttribute("currentMaxPrice", maxPrice);
        model.addAttribute("currentMinArea", minArea);
        model.addAttribute("currentMaxArea", maxArea);
        
        return "projects/list";
    }
    
    @GetMapping("/{slug}")
    public String projectDetail(@PathVariable String slug, Model model) {
        Optional<Project> projectOpt = projectService.findBySlug(slug);
        
        if (projectOpt.isEmpty()) {
            return "redirect:/projects";
        }
        
        Project project = projectOpt.get();
        
        // Increment view count
        projectService.incrementViewCount(project.getId());
        
        // Get project images
        List<ProjectImage> projectImages = projectService.findImagesByProjectId(project.getId());
        
        // Find similar projects (same type or same ward)
        List<Project> similarProjects = projectService.findByProjectType(project.getProjectType())
                .stream()
                .filter(p -> !p.getId().equals(project.getId()))
                .limit(4)
                .toList();
        
        model.addAttribute("project", project);
        model.addAttribute("projectImages", projectImages);
        model.addAttribute("similarProjects", similarProjects);
        
        return "projects/detail";
    }
    
    @GetMapping("/type/{type}")
    public String projectsByType(@PathVariable String type, Model model) {
        try {
            ProjectType projectType = ProjectType.valueOf(type.toUpperCase());
            List<Project> projects = projectService.findByProjectType(projectType);
            
            model.addAttribute("projects", projects);
            model.addAttribute("pageTitle", "Dự án " + projectType.getDisplayName());
            model.addAttribute("currentType", type);
            
            return "projects/list";
        } catch (IllegalArgumentException e) {
            return "redirect:/projects";
        }
    }
    
    @GetMapping("/ward/{ward}")
    public String projectsByWard(@PathVariable String ward, Model model) {
        List<Project> projects = projectService.findByWard(ward);
        
        model.addAttribute("projects", projects);
        model.addAttribute("pageTitle", "Dự án tại " + ward);
        model.addAttribute("currentWard", ward);
        
        return "projects/list";
    }
    
    @GetMapping("/featured")
    public String featuredProjects(Model model) {
        List<Project> projects = projectService.findFeaturedProjects();
        
        model.addAttribute("projects", projects);
        model.addAttribute("pageTitle", "Dự án nổi bật");
        
        return "projects/list";
    }
    
    @GetMapping("/hot")
    public String hotProjects(Model model) {
        List<Project> projects = projectService.findHotProjects();
        
        model.addAttribute("projects", projects);
        model.addAttribute("pageTitle", "Dự án hot");
        
        return "projects/list";
    }
}
