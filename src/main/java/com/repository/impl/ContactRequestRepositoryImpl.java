package com.repository.impl;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entities.ContactRequest;
import com.entities.RequestStatus;
import com.repository.ContactRequestRepository;

@Repository
@Transactional
public class ContactRequestRepositoryImpl implements ContactRequestRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public ContactRequest save(ContactRequest contactRequest) {
        Session session = sessionFactory.getCurrentSession();
        
        // Use merge instead of saveOrUpdate for better handling
        ContactRequest savedRequest = (ContactRequest) session.merge(contactRequest);
        
        // Force flush to ensure data is written to database
        session.flush();
        
        return savedRequest;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactRequest> findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        ContactRequest contactRequest = session.get(ContactRequest.class, id);
        return Optional.ofNullable(contactRequest);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM ContactRequest c ORDER BY c.createdAt DESC", ContactRequest.class)
                     .list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByStatus(RequestStatus status) {
        Session session = sessionFactory.getCurrentSession();
        Query<ContactRequest> query = session.createQuery(
            "FROM ContactRequest c WHERE c.status = :status ORDER BY c.createdAt DESC", 
            ContactRequest.class);
        query.setParameter("status", status);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByProjectId(Integer projectId) {
        Session session = sessionFactory.getCurrentSession();
        Query<ContactRequest> query = session.createQuery(
            "FROM ContactRequest c WHERE c.project.id = :projectId ORDER BY c.createdAt DESC", 
            ContactRequest.class);
        query.setParameter("projectId", projectId);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ContactRequest> findByAssignedToId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<ContactRequest> query = session.createQuery(
            "FROM ContactRequest c WHERE c.assignedTo.id = :userId ORDER BY c.createdAt DESC", 
            ContactRequest.class);
        query.setParameter("userId", userId);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countByStatus(RequestStatus status) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
            "SELECT COUNT(c) FROM ContactRequest c WHERE c.status = :status", Long.class);
        query.setParameter("status", status);
        return query.getSingleResult();
    }
    
    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        ContactRequest contactRequest = session.get(ContactRequest.class, id);
        if (contactRequest != null) {
            session.delete(contactRequest);
            session.flush();
        }
    }
}