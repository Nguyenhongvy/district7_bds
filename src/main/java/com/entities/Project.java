package com.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Size(max = 200)
    private String name;
    
    @Size(max = 250)
    @Column(unique = true)
    private String slug;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "developer_id")
    private Developer developer;
    
    @NotBlank
    @Size(max = 500)
    private String address;
    
    @NotBlank
    @Size(max = 100)
    private String ward;
    
    @NotNull
    @DecimalMin("0.0")
    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;
    
    @NotNull
    @DecimalMin("0.0")
    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private ProjectType projectType;
    
    @Column(name = "price_from", precision = 15, scale = 0)
    private BigDecimal priceFrom;
    
    @Column(name = "price_to", precision = 15, scale = 0)
    private BigDecimal priceTo;
    
    @Column(name = "area_from", precision = 10, scale = 2)
    private BigDecimal areaFrom;
    
    @Column(name = "area_to", precision = 10, scale = 2)
    private BigDecimal areaTo;
    
    @Column(name = "total_units")
    private Integer totalUnits;
    
    @Column(name = "available_units")
    private Integer availableUnits;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String amenities;
    
    @Column(name = "completion_year")
    private Integer completionYear;
    
    @Column(name = "handover_year")
    private Integer handoverYear;
    
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PLANNING;
    
    @Size(max = 500)
    private String thumbnail;
    
    @Column(name = "video_url")
    @Size(max = 500)
    private String videoUrl;
    
    @Column(name = "virtual_tour_url")
    @Size(max = 500)
    private String virtualTourUrl;
    
    @Column(name = "is_featured")
    private Boolean isFeatured = false;
    
    @Column(name = "is_hot")
    private Boolean isHot = false;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "view_count")
    private Integer viewCount = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectImage> images;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectAmenity> projectAmenities;
    
    // Constructors
    public Project() {}
    
    public Project(String name, String address, String ward, BigDecimal latitude, BigDecimal longitude, ProjectType projectType) {
        this.name = name;
        this.address = address;
        this.ward = ward;
        this.latitude = latitude;
        this.longitude = longitude;
        this.projectType = projectType;
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public Developer getDeveloper() { return developer; }
    public void setDeveloper(Developer developer) { this.developer = developer; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    
    public ProjectType getProjectType() { return projectType; }
    public void setProjectType(ProjectType projectType) { this.projectType = projectType; }
    
    public BigDecimal getPriceFrom() { return priceFrom; }
    public void setPriceFrom(BigDecimal priceFrom) { this.priceFrom = priceFrom; }
    
    public BigDecimal getPriceTo() { return priceTo; }
    public void setPriceTo(BigDecimal priceTo) { this.priceTo = priceTo; }
    
    public BigDecimal getAreaFrom() { return areaFrom; }
    public void setAreaFrom(BigDecimal areaFrom) { this.areaFrom = areaFrom; }
    
    public BigDecimal getAreaTo() { return areaTo; }
    public void setAreaTo(BigDecimal areaTo) { this.areaTo = areaTo; }
    
    public Integer getTotalUnits() { return totalUnits; }
    public void setTotalUnits(Integer totalUnits) { this.totalUnits = totalUnits; }
    
    public Integer getAvailableUnits() { return availableUnits; }
    public void setAvailableUnits(Integer availableUnits) { this.availableUnits = availableUnits; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    
    public Integer getCompletionYear() { return completionYear; }
    public void setCompletionYear(Integer completionYear) { this.completionYear = completionYear; }
    
    public Integer getHandoverYear() { return handoverYear; }
    public void setHandoverYear(Integer handoverYear) { this.handoverYear = handoverYear; }
    
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
    
    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
    
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    
    public String getVirtualTourUrl() { return virtualTourUrl; }
    public void setVirtualTourUrl(String virtualTourUrl) { this.virtualTourUrl = virtualTourUrl; }
    
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
    
    public Boolean getIsHot() { return isHot; }
    public void setIsHot(Boolean isHot) { this.isHot = isHot; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
    
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<ProjectImage> getImages() { return images; }
    public void setImages(List<ProjectImage> images) { this.images = images; }
    
    public List<Unit> getUnits() { return units; }
    public void setUnits(List<Unit> units) { this.units = units; }
    
    public List<ProjectAmenity> getProjectAmenities() { return projectAmenities; }
    public void setProjectAmenities(List<ProjectAmenity> projectAmenities) { this.projectAmenities = projectAmenities; }
}