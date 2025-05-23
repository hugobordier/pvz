package com.epf.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.core.model.Map;
import com.epf.infrastructure.dao.MapDAO;
import com.epf.infrastructure.mapper.MapEntityMapper;

@Repository
public class MapRepository {
    
    private final MapDAO mapDAO;
    private final MapEntityMapper mapper;
    
    @Autowired
    public MapRepository(MapDAO mapDAO, MapEntityMapper mapper) {
        this.mapDAO = mapDAO;
        this.mapper = mapper;
    }
    
    public List<Map> findAll() {
        return mapper.mapListEntityToListModel(mapDAO.findAll());
    }
    
    public Map findById(Integer id) {
        return mapper.mapEntityToModel(mapDAO.findById(id));
    }

     public Map save(Map map) {
        try {
            Map savedMap = mapDAO.save(map);
            return savedMap;
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            mapDAO.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }
}