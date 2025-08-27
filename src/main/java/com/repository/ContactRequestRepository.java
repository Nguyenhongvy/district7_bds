package com.repository;

import java.util.List;
import java.util.Optional;

import com.entities.ContactRequest;
import com.entities.RequestStatus;

public interface ContactRequestRepository {
    ContactRequest save(ContactRequest contactRequest);
    Optional<ContactRequest> findById(Integer id);
    List<ContactRequest> findAll();
    List<ContactRequest> findByStatus(RequestStatus status);
    List<ContactRequest> findByProjectId(Integer projectId);
    List<ContactRequest> findByAssignedToId(Integer userId);
    Long countByStatus(RequestStatus status);
    void deleteById(Integer id);
}