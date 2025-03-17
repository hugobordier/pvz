package com.epf.core.service;

import com.epf.core.model.Map;
import com.epf.infrastructure.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    
    private final MapRepository mapRepository;
    
    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }
    
    public List<Map> findAll() {
        return mapRepository.findAll();
    }
    
    public Map findById(Integer id) {
        return mapRepository.findById(id);
    }
}