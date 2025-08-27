package com.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.entities.AmenityCategory;
import com.entities.ProjectAmenity;

public interface ProjectAmenityRepository {
	ProjectAmenity save(ProjectAmenity projectAmenity);

	Optional<ProjectAmenity> findById(Integer id);

	List<ProjectAmenity> findByProjectId(Integer projectId);

	List<ProjectAmenity> findByProjectIdAndAmenityCategory(Integer projectId, AmenityCategory category);

	List<ProjectAmenity> findByAmenityId(Integer amenityId);

	List<ProjectAmenity> findByProjectIdAndDistanceLessThan(Integer projectId, BigDecimal maxDistance);

	void deleteById(Integer id);

	void deleteByProjectId(Integer projectId);

	void deleteByProjectIdAndAmenityId(Integer projectId, Integer amenityId);
}
