package com.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Ward;
import com.repository.WardRepository;
@Repository
@Transactional
public class WardRepositoryImp implements WardRepository{
	@Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Ward> findAll() {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward", Ward.class);
        return query.getResultList();
    }
    
    @Override
    public Ward findById(Integer id) {
        Session session = getCurrentSession();
        return session.get(Ward.class, id);
    }
    
    @Override
    public Ward findByName(String name) {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward w WHERE w.name = :name", Ward.class);
        query.setParameter("name", name);
        List<Ward> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
    
    @Override
    public List<Ward> findByNameContaining(String name) {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward w WHERE LOWER(w.name) LIKE LOWER(:name)", Ward.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Ward> findAllOrderedByName() {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward w ORDER BY w.name ASC", Ward.class);
        return query.getResultList();
    }
    
    @Override
    public List<Ward> findByPopulationBetween(Integer minPopulation, Integer maxPopulation) {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward w WHERE w.population BETWEEN :min AND :max", Ward.class);
        query.setParameter("min", minPopulation);
        query.setParameter("max", maxPopulation);
        return query.getResultList();
    }
    
    @Override
    public List<Ward> findByAreaBetween(Double minArea, Double maxArea) {
        Session session = getCurrentSession();
        Query<Ward> query = session.createQuery("FROM Ward w WHERE w.areaSqkm BETWEEN :min AND :max", Ward.class);
        query.setParameter("min", minArea);
        query.setParameter("max", maxArea);
        return query.getResultList();
    }
    
    @Override
    public Ward save(Ward ward) {
        Session session = getCurrentSession();
        session.save(ward);
        return ward;
    }
    
    @Override
    public Ward update(Ward ward) {
        Session session = getCurrentSession();
        session.update(ward);
        return ward;
    }
    
    @Override
    public void deleteById(Integer id) {
        Session session = getCurrentSession();
        Ward ward = session.get(Ward.class, id);
        if (ward != null) {
            session.delete(ward);
        }
    }
    
    @Override
    public boolean existsByName(String name) {
        Session session = getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(w) FROM Ward w WHERE w.name = :name", Long.class);
        query.setParameter("name", name);
        Long count = query.getSingleResult();
        return count > 0;
    }
    
    @Override
    public long countTotal() {
        Session session = getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(w) FROM Ward w", Long.class);
        return query.getSingleResult();
    }
    
    @Override
    public List<String> findAllWardNames() {
        Session session = getCurrentSession();
        Query<String> query = session.createQuery("SELECT w.name FROM Ward w ORDER BY w.name ASC", String.class);
        return query.getResultList();
    }
}
