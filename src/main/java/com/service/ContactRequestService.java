package com.service;

import java.util.List;
import java.util.Optional;
import com.entities.ContactRequest;
import com.entities.RequestStatus;
import com.entities.RequestType;

public interface ContactRequestService {

    ContactRequest save(ContactRequest contactRequest);

    Optional<ContactRequest> findById(Integer id);

    List<ContactRequest> findAll();

    List<ContactRequest> findByStatus(RequestStatus status);
    
    List<ContactRequest> findByProjectId(Integer projectId);
    
    List<ContactRequest> findByAssignedToId(Integer userId);

    Long countByStatus(RequestStatus status);
    
    ContactRequest updateStatus(Integer id, RequestStatus status, String notes);
    
    ContactRequest assignToUser(Integer id, Integer userId, String notes);
    

    void deleteById(Integer id);
    
    ContactRequest createFromForm(String fullName, String phone, String email, 
                                String message, Integer projectId, RequestType requestType);
}