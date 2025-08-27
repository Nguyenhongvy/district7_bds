package com.repository.impl;

import com.entities.Developer;
import com.repository.DeveloperRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DeveloperRepositoryImpl implements DeveloperRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("deprecation")
	@Override
    public Developer save(Developer developer) {
        sessionFactory.getCurrentSession().saveOrUpdate(developer);
        return developer;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Developer> findById(Integer id) {
        Developer developer = sessionFactory.getCurrentSession().get(Developer.class, id);
        return Optional.ofNullable(developer);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> findAll() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM Developer d ORDER BY d.name", Developer.class)
            .list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> findByIsActive(Boolean isActive) {
        Query<Developer> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Developer d WHERE d.isActive = :isActive ORDER BY d.name", Developer.class);
        query.setParameter("isActive", isActive);
        return query.list();
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void deleteById(Integer id) {
        Developer developer = sessionFactory.getCurrentSession().get(Developer.class, id);
        if (developer != null) {
            sessionFactory.getCurrentSession().delete(developer);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> findByNameContaining(String name) {
        Query<Developer> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Developer d WHERE LOWER(d.name) LIKE LOWER(:name) ORDER BY d.name", Developer.class);
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }
}

