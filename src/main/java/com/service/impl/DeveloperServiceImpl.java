package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Developer;
import com.repository.DeveloperRepository;
import com.service.DeveloperService;

@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService {
    
    @Autowired
    private DeveloperRepository developerRepository;
    
    @Override
    public Developer createDeveloper(Developer developer) {
        developer.setIsActive(true);
        return developerRepository.save(developer);
    }
    
    @Override
    public Developer updateDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Developer> findById(Integer id) {
        return developerRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> findAll() {
        return developerRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> findActiveDevelopers() {
        return developerRepository.findByIsActive(true);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Developer> searchByName(String name) {
        return developerRepository.findByNameContaining(name);
    }
    
    @Override
    public void deleteDeveloper(Integer id) {
        developerRepository.deleteById(id);
    }
    
    @Override
    public void activateDeveloper(Integer id) {
        Optional<Developer> developer = developerRepository.findById(id);
        if (developer.isPresent()) {
            developer.get().setIsActive(true);
            developerRepository.save(developer.get());
        }
    }
    
    @Override
    public void deactivateDeveloper(Integer id) {
        Optional<Developer> developer = developerRepository.findById(id);
        if (developer.isPresent()) {
            developer.get().setIsActive(false);
            developerRepository.save(developer.get());
        }
    }
}

