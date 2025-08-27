package com.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Project;
import com.entities.ProjectImage;
import com.entities.ProjectStatus;
import com.entities.ProjectType;
import com.repository.ProjectRepository;

@Repository
@Transactional
public class ProjectRepositoryImpl implements ProjectRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Project save(Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
        return project;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Integer id) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, id);
        return Optional.ofNullable(project);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findBySlug(String slug) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.slug = :slug", Project.class);
        query.setParameter("slug", slug);
        return query.uniqueResultOptional();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM Project p ORDER BY p.createdAt DESC", Project.class)
            .list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByIsActive(Boolean isActive) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.isActive = :isActive ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("isActive", isActive);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByIsFeatured(Boolean isFeatured) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.isFeatured = :isFeatured AND p.isActive = true ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("isFeatured", isFeatured);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByIsHot(Boolean isHot) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.isHot = :isHot AND p.isActive = true ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("isHot", isHot);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByWard(String ward) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.ward = :ward AND p.isActive = true ORDER BY p.name", Project.class);
        query.setParameter("ward", ward);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByProjectType(ProjectType projectType) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.projectType = :projectType AND p.isActive = true ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("projectType", projectType);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByStatus(ProjectStatus status) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.status = :status AND p.isActive = true ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("status", status);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByDeveloperId(Integer developerId) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.developer.id = :developerId AND p.isActive = true ORDER BY p.createdAt DESC", Project.class);
        query.setParameter("developerId", developerId);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByNameContaining(String name) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE LOWER(p.name) LIKE LOWER(:name) AND p.isActive = true ORDER BY p.name", Project.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByAddressContaining(String address) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE LOWER(p.address) LIKE LOWER(:address) AND p.isActive = true ORDER BY p.name", Project.class);
        query.setParameter("address", "%" + address + "%");
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String hql = "FROM Project p WHERE p.isActive = true";
        if (minPrice != null) {
            hql += " AND p.priceFrom >= :minPrice";
        }
        if (maxPrice != null) {
            hql += " AND p.priceTo <= :maxPrice";
        }
        hql += " ORDER BY p.priceFrom";
        
        Query<Project> query = sessionFactory.getCurrentSession().createQuery(hql, Project.class);
        if (minPrice != null) {
            query.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findByAreaRange(BigDecimal minArea, BigDecimal maxArea) {
        String hql = "FROM Project p WHERE p.isActive = true";
        if (minArea != null) {
            hql += " AND p.areaFrom >= :minArea";
        }
        if (maxArea != null) {
            hql += " AND p.areaTo <= :maxArea";
        }
        hql += " ORDER BY p.areaFrom";
        
        Query<Project> query = sessionFactory.getCurrentSession().createQuery(hql, Project.class);
        if (minArea != null) {
            query.setParameter("minArea", minArea);
        }
        if (maxArea != null) {
            query.setParameter("maxArea", maxArea);
        }
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findProjectsInBounds(BigDecimal swLat, BigDecimal swLng, BigDecimal neLat, BigDecimal neLng) {
        Query<Project> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Project p WHERE p.latitude BETWEEN :swLat AND :neLat " +
                        "AND p.longitude BETWEEN :swLng AND :neLng " +
                        "AND p.isActive = true", Project.class);
        query.setParameter("swLat", swLat);
        query.setParameter("swLng", swLng);
        query.setParameter("neLat", neLat);
        query.setParameter("neLng", neLng);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> findNearbyProjects(BigDecimal latitude, BigDecimal longitude, Double radiusKm) {
        // Using Haversine formula approximation for nearby search
        String hql = "FROM Project p WHERE p.isActive = true " +
                    "AND (6371 * acos(cos(radians(:lat)) * cos(radians(p.latitude)) * " +
                    "cos(radians(p.longitude) - radians(:lng)) + " +
                    "sin(radians(:lat)) * sin(radians(p.latitude)))) <= :radius " +
                    "ORDER BY (6371 * acos(cos(radians(:lat)) * cos(radians(p.latitude)) * " +
                    "cos(radians(p.longitude) - radians(:lng)) + " +
                    "sin(radians(:lat)) * sin(radians(p.latitude))))";
        
        Query<Project> query = sessionFactory.getCurrentSession().createQuery(hql, Project.class);
        query.setParameter("lat", latitude);
        query.setParameter("lng", longitude);
        query.setParameter("radius", radiusKm);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countByStatus(ProjectStatus status) {
        Query<Long> query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(p) FROM Project p WHERE p.status = :status AND p.isActive = true", Long.class);
        query.setParameter("status", status);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countByProjectType(ProjectType projectType) {
        Query<Long> query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(p) FROM Project p WHERE p.projectType = :projectType AND p.isActive = true", Long.class);
        query.setParameter("projectType", projectType);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countByWard(String ward) {
        Query<Long> query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(p) FROM Project p WHERE p.ward = :ward AND p.isActive = true", Long.class);
        query.setParameter("ward", ward);
        return query.getSingleResult();
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void deleteById(Integer id) {
        Project project = sessionFactory.getCurrentSession().get(Project.class, id);
        if (project != null) {
            sessionFactory.getCurrentSession().delete(project);
        }
    }
    
    @Override
    public void incrementViewCount(Integer projectId) {
        Query query = sessionFactory.getCurrentSession()
            .createQuery("UPDATE Project p SET p.viewCount = p.viewCount + 1 WHERE p.id = :projectId");
        query.setParameter("projectId", projectId);
        query.executeUpdate();
    }

	@Override
	public List<ProjectImage> findImagesByProjectId(Integer projectId) {
	    Query<ProjectImage> query = sessionFactory.getCurrentSession()
	        .createQuery("FROM ProjectImage pi WHERE pi.project.id = :projectId ORDER BY pi.displayOrder ASC, pi.id ASC", ProjectImage.class);
	    query.setParameter("projectId", projectId);
	    return query.list();
	}
}