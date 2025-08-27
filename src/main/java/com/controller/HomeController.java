package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entities.Project;
import com.service.ProjectService;


@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public String home(Model model) {
        List<Project> featuredProjects = projectService.findFeaturedProjects();
        List<Project> hotProjects = projectService.findHotProjects();
        List<Project> recentProjects = projectService.findActiveProjects()
                .stream()
                .limit(6)
                .toList();
        
        model.addAttribute("featuredProjects", featuredProjects);
        model.addAttribute("hotProjects", hotProjects);
        model.addAttribute("recentProjects", recentProjects);
        
        return "home";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}