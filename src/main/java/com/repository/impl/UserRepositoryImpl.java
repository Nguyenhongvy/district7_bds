package com.repository.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.User;
import com.entities.UserRole;
import com.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("deprecation")
	@Override
    public User save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Integer id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        return Optional.ofNullable(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        Query<User> query = sessionFactory.getCurrentSession()
            .createQuery("FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        Query<User> query = sessionFactory.getCurrentSession()
            .createQuery("FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM User u ORDER BY u.createdAt DESC", User.class)
            .list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findByRole(UserRole role) {
        Query<User> query = sessionFactory.getCurrentSession()
            .createQuery("FROM User u WHERE u.role = :role ORDER BY u.createdAt DESC", User.class);
        query.setParameter("role", role);
        return query.list();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findByIsActive(Boolean isActive) {
        Query<User> query = sessionFactory.getCurrentSession()
            .createQuery("FROM User u WHERE u.isActive = :isActive ORDER BY u.createdAt DESC", User.class);
        query.setParameter("isActive", isActive);
        return query.list();
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void deleteById(Integer id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        Query<Long> query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        Query<Long> query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }
}