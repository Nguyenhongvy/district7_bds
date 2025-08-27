package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entities.Developer;
import com.entities.Project;
import com.entities.ProjectStatus;
import com.entities.ProjectType;
import com.entities.User;
import com.entities.UserRole;
import com.service.DeveloperService;
import com.service.ProjectService;
import com.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private DeveloperService developerService;
    
    @Autowired
    private UserService userService;
    
    private final String UPLOAD_DIR = "uploads/";
    
    @GetMapping
    public String adminDashboard(Model model) {
        // Dashboard statistics
        Long totalProjects = projectService.getTotalActiveProjects();
        Long apartmentCount = projectService.getProjectCountByType(ProjectType.APARTMENT);
        Long villaCount = projectService.getProjectCountByType(ProjectType.VILLA);
        Long officeCount = projectService.getProjectCountByType(ProjectType.OFFICE);
        
        model.addAttribute("totalProjects", totalProjects);
        model.addAttribute("apartmentCount", apartmentCount);
        model.addAttribute("villaCount", villaCount);
        model.addAttribute("officeCount", officeCount);
        
        // Recent projects
        List<Project> recentProjects = projectService.findAll().stream().limit(5).toList();
        model.addAttribute("recentProjects", recentProjects);
        
        return "admin/dashboard";
    }
    
    // Project Management
    @GetMapping("/projects")
    public String projectManagement(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "admin/projects/list";
    }
    
    @GetMapping("/projects/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("developers", developerService.findActiveDevelopers());
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("projectStatuses", ProjectStatus.values());
        
        // District 7 wards
        String[] wards = {
            "Phường Tân Thuận Đông", "Phường Tân Thuận Tây", "Phường Tân Kiểng",
            "Phường Tân Hưng", "Phường Bình Thuận", "Phường Tân Phong",
            "Phường Tân Phú", "Phường Tân Quy", "Phường Phú Thuận", "Phường Phú Mỹ"
        };
        model.addAttribute("wards", wards);
        
        return "admin/projects/form";
    }
    
    @PostMapping("/projects/create")
    public String createProject(@ModelAttribute Project project,
                               @RequestParam(required = false) MultipartFile thumbnailFile,
                               RedirectAttributes redirectAttributes) {
        try {
            // Handle file upload
            if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
                String fileName = saveUploadedFile(thumbnailFile, "projects");
                project.setThumbnail("/uploads/projects/" + fileName);
            }
            
            projectService.createProject(project);
            redirectAttributes.addFlashAttribute("success", "Tạo dự án thành công!");
            
            return "redirect:/admin/projects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
            return "redirect:/admin/projects/create";
        }
    }
    
    @GetMapping("/projects/edit/{id}")
    public String editProjectForm(@PathVariable Integer id, Model model) {
        Optional<Project> project = projectService.findById(id);
        if (project.isEmpty()) {
            return "redirect:/admin/projects";
        }
        
        model.addAttribute("project", project.get());
        model.addAttribute("developers", developerService.findActiveDevelopers());
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("projectStatuses", ProjectStatus.values());
        
        String[] wards = {
            "Phường Tân Thuận Đông", "Phường Tân Thuận Tây", "Phường Tân Kiểng",
            "Phường Tân Hưng", "Phường Bình Thuận", "Phường Tân Phong",
            "Phường Tân Phú", "Phường Tân Quy", "Phường Phú Thuận", "Phường Phú Mỹ"
        };
        model.addAttribute("wards", wards);
        
        return "admin/projects/form";
    }
    
    @PostMapping("/projects/edit/{id}")
    public String updateProject(@PathVariable Integer id,
                               @ModelAttribute Project project,
                               @RequestParam(required = false) MultipartFile thumbnailFile,
                               RedirectAttributes redirectAttributes) {
        try {
            project.setId(id);
            
            // Handle file upload
            if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
                String fileName = saveUploadedFile(thumbnailFile, "projects");
                project.setThumbnail("/uploads/projects/" + fileName);
            }
            
            projectService.updateProject(project);
            redirectAttributes.addFlashAttribute("success", "Cập nhật dự án thành công!");
            
            return "redirect:/admin/projects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
            return "redirect:/admin/projects/edit/" + id;
        }
    }
    
    @PostMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteProject(id);
            redirectAttributes.addFlashAttribute("success", "Xóa dự án thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/projects";
    }
    @PostMapping("/projects/toggle-status/{id}")
    public String toggleProjectStatus(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Project> project = projectService.findById(id);
            if (project.isPresent()) {
                if (project.get().getIsActive()) {
                    projectService.deactivateProject(id);
                    redirectAttributes.addFlashAttribute("success", "Đã ẩn dự án!");
                } else {
                    projectService.activateProject(id);
                    redirectAttributes.addFlashAttribute("success", "Đã kích hoạt dự án!");
                }
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/projects";
    }
    
    // Developer Management
    @GetMapping("/developers")
    public String developerManagement(Model model) {
        List<Developer> developers = developerService.findAll();
        model.addAttribute("developers", developers);
        return "admin/developers/list";
    }
    
    @GetMapping("/developers/create")
    public String createDeveloperForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "admin/developers/form";
    }
    
    @PostMapping("/developers/create")
    public String createDeveloper(@ModelAttribute Developer developer,
                                 @RequestParam(required = false) MultipartFile logoFile,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (logoFile != null && !logoFile.isEmpty()) {
                String fileName = saveUploadedFile(logoFile, "developers");
                developer.setLogoUrl("/uploads/developers/" + fileName);
            }
            
            developerService.createDeveloper(developer);
            redirectAttributes.addFlashAttribute("success", "Tạo chủ đầu tư thành công!");
            
            return "redirect:/admin/developers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
            return "redirect:/admin/developers/create";
        }
    }
    
    // User Management
    @GetMapping("/users")
    public String userManagement(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }
    
    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userRoles", UserRole.values());
        return "admin/users/form";
    }
    
    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("success", "Tạo người dùng thành công!");
            return "redirect:/admin/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
            return "redirect:/admin/users/create";
        }
    }
    
    // Utility method for file upload
    private String saveUploadedFile(MultipartFile file, String subDirectory) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        // Create directory if not exists
        Path uploadPath = Paths.get(UPLOAD_DIR + subDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Save file
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        
        return fileName;
    }
}
