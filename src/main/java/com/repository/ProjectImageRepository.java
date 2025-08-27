package com.repository;

import java.util.List;
import java.util.Optional;

import com.entities.ImageType;
import com.entities.ProjectImage;

public interface ProjectImageRepository {
    ProjectImage save(ProjectImage projectImage);
    Optional<ProjectImage> findById(Integer id);
    List<ProjectImage> findByProjectId(Integer projectId);
    List<ProjectImage> findByProjectIdAndImageType(Integer projectId, ImageType imageType);
    List<ProjectImage> findByProjectIdOrderByDisplayOrder(Integer projectId);
    Optional<ProjectImage> findByProjectIdAndIsPrimary(Integer projectId, Boolean isPrimary);
    void deleteById(Integer id);
    void deleteByProjectId(Integer projectId);
}


