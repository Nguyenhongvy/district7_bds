package com.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "units", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"project_id", "unit_code"})
})
public class Unit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @NotBlank
    @Size(max = 50)
    @Column(name = "unit_code")
    private String unitCode;
    
    @Size(max = 50)
    @Column(name = "unit_type")
    private String unitType;
    
    @NotNull
    @DecimalMin("0.0")
    @Column(precision = 10, scale = 2)
    private BigDecimal area;
    
    @Column(precision = 15, scale = 0)
    private BigDecimal price;
    
    @Column(name = "price_per_sqm", precision = 15, scale = 0)
    private BigDecimal pricePerSqm;
    
    @Column(name = "floor_number")
    private Integer floorNumber;
    
    @Size(max = 50)
    private String tower;
    
    @Enumerated(EnumType.STRING)
    private Direction direction;
    
    @Column(name = "view_description")
    @Size(max = 200)
    private String viewDescription;
    
    private Integer bedrooms;
    
    private Integer bathrooms;
    
    private Boolean balcony = false;
    
    @Enumerated(EnumType.STRING)
    private UnitStatus status = UnitStatus.AVAILABLE;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "floor_plan_url")
    @Size(max = 500)
    private String floorPlanUrl;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public Unit() {}
    
    @SuppressWarnings("deprecation")
	public Unit(Project project, String unitCode, BigDecimal area, BigDecimal price) {
        this.project = project;
        this.unitCode = unitCode;
        this.area = area;
        this.price = price;
        if (price != null && area != null && area.compareTo(BigDecimal.ZERO) > 0) {
            this.pricePerSqm = price.divide(area, 0, BigDecimal.ROUND_HALF_UP);
        }
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    
    public String getUnitCode() { return unitCode; }
    public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
    
    public String getUnitType() { return unitType; }
    public void setUnitType(String unitType) { this.unitType = unitType; }
    
    public BigDecimal getArea() { return area; }
    public void setArea(BigDecimal area) { 
        this.area = area;
        updatePricePerSqm();
    }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { 
        this.price = price;
        updatePricePerSqm();
    }
    
    @SuppressWarnings("deprecation")
	private void updatePricePerSqm() {
        if (price != null && area != null && area.compareTo(BigDecimal.ZERO) > 0) {
            this.pricePerSqm = price.divide(area, 0, BigDecimal.ROUND_HALF_UP);
        }
    }
    
    public BigDecimal getPricePerSqm() { return pricePerSqm; }
    public void setPricePerSqm(BigDecimal pricePerSqm) { this.pricePerSqm = pricePerSqm; }
    
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
    
    public String getTower() { return tower; }
    public void setTower(String tower) { this.tower = tower; }
    
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }
    
    public String getViewDescription() { return viewDescription; }
    public void setViewDescription(String viewDescription) { this.viewDescription = viewDescription; }
    
    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }
    
    public Integer getBathrooms() { return bathrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }
    
    public Boolean getBalcony() { return balcony; }
    public void setBalcony(Boolean balcony) { this.balcony = balcony; }
    
    public UnitStatus getStatus() { return status; }
    public void setStatus(UnitStatus status) { this.status = status; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getFloorPlanUrl() { return floorPlanUrl; }
    public void setFloorPlanUrl(String floorPlanUrl) { this.floorPlanUrl = floorPlanUrl; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
