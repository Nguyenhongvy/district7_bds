package com.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "project_amenities")
public class ProjectAmenity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;
    
    @DecimalMin("0.0")
    @Column(name = "distance_km", precision = 5, scale = 2)
    private BigDecimal distanceKm;
    
    @Column(name = "walking_time")
    private Integer walkingTime; // minutes
    
    @Size(max = 200)
    private String description;
    
    @DecimalMin("0.0")
    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;
    
    @DecimalMin("0.0")
    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;
    
    // Constructors
    public ProjectAmenity() {}
    
    public ProjectAmenity(Project project, Amenity amenity, BigDecimal distanceKm) {
        this.project = project;
        this.amenity = amenity;
        this.distanceKm = distanceKm;
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    
    public Amenity getAmenity() { return amenity; }
    public void setAmenity(Amenity amenity) { this.amenity = amenity; }
    
    public BigDecimal getDistanceKm() { return distanceKm; }
    public void setDistanceKm(BigDecimal distanceKm) { this.distanceKm = distanceKm; }
    
    public Integer getWalkingTime() { return walkingTime; }
    public void setWalkingTime(Integer walkingTime) { this.walkingTime = walkingTime; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
}