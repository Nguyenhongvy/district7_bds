package com.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wards")
public class Ward {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Lob
    private String description;
    
    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;
    
    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;
    
    private Integer population;
    
    @Column(name = "area_sqkm", precision = 8, scale = 2)
    private BigDecimal areaSqkm;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public Ward() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Ward(String name) {
        this();
        this.name = name;
    }
    
    public Ward(String name, String description, BigDecimal latitude, BigDecimal longitude) {
        this(name);
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    public Integer getPopulation() {
        return population;
    }
    
    public void setPopulation(Integer population) {
        this.population = population;
    }
    
    public BigDecimal getAreaSqkm() {
        return areaSqkm;
    }
    
    public void setAreaSqkm(BigDecimal areaSqkm) {
        this.areaSqkm = areaSqkm;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Ward{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                ", areaSqkm=" + areaSqkm +
                ", createdAt=" + createdAt +
                '}';
    }
}