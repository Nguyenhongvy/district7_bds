package com.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.ContactRequest;
import com.entities.Project;
import com.entities.RequestStatus;
import com.entities.RequestType;
import com.entities.User;
import com.repository.ContactRequestRepository;
import com.repository.ProjectRepository;
import com.repository.UserRepository;
import com.service.ContactRequestService;

@Service
@Transactional
public class ContactRequestServiceImpl implements ContactRequestService {
    
    @Autowired
    private ContactRequestRepository contactRequestRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public ContactRequest save(ContactRequest contactRequest) {
        if (contactRequest.getCreatedAt() == null) {
            contactRequest.setCreatedAt(LocalDateTime.now());
        }
        contactRequest.setUpdatedAt(LocalDateTime.now());
        
        if (contactRequest.getStatus() == null) {
            contactRequest.setStatus(RequestStatus.NEW);
        }
        
        return contactRequestRepository.save(contactRequest);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactRequest> findById(Integer id) {
        return contactRequestRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findAll() {
        return contactRequestRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByStatus(RequestStatus status) {
        return contactRequestRepository.findByStatus(status);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByProjectId(Integer projectId) {
        return contactRequestRepository.findByProjectId(projectId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByAssignedToId(Integer userId) {
        return contactRequestRepository.findByAssignedToId(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countByStatus(RequestStatus status) {
        return contactRequestRepository.countByStatus(status);
    }
    
    @Override
    public ContactRequest updateStatus(Integer id, RequestStatus status, String notes) {
        Optional<ContactRequest> optionalRequest = contactRequestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            ContactRequest request = optionalRequest.get();
            request.setStatus(status);
            if (notes != null && !notes.trim().isEmpty()) {
                request.setNotes(notes);
            }
            request.setUpdatedAt(LocalDateTime.now());
            return contactRequestRepository.save(request);
        }
        throw new RuntimeException("Contact request not found with id: " + id);
    }
    
    @Override
    public ContactRequest assignToUser(Integer id, Integer userId, String notes) {
        Optional<ContactRequest> optionalRequest = contactRequestRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalRequest.isPresent() && optionalUser.isPresent()) {
            ContactRequest request = optionalRequest.get();
            request.setAssignedTo(optionalUser.get());
            if (notes != null && !notes.trim().isEmpty()) {
                request.setNotes(notes);
            }
            request.setUpdatedAt(LocalDateTime.now());
            return contactRequestRepository.save(request);
        }
        throw new RuntimeException("Contact request or user not found");
    }
    
    @Override
    public void deleteById(Integer id) {
        contactRequestRepository.deleteById(id);
    }
    
    @Override
    public ContactRequest createFromForm(String fullName, String phone, String email, 
                                       String message, Integer projectId, RequestType requestType) {
        ContactRequest request = new ContactRequest();
        request.setFullName(fullName);
        request.setPhone(phone);
        request.setEmail(email);
        request.setMessage(message);
        System.out.println("projectId: " + projectId);
        if (projectId != null) {
            Optional<Project> project = projectRepository.findById(projectId);
            System.out.println("project: " + project);
            if (project.isPresent()) {
                request.setProject(project.get());
            }
        }
        
        if (requestType == null) {
            requestType = RequestType.CONSULTATION;
        }
        request.setRequestType(requestType);
        
        return save(request);
    }
}