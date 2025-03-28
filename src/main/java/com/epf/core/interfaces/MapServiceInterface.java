package com.epf.core.interfaces;

import java.util.List;

import com.epf.core.model.Map;

public interface MapServiceInterface {

    List<Map> findAll();
    
    Map findById(Integer id);
    
    Map createMap(Map map);
    
    Map updateMap(Map updatedMap);
    
    void delete(Integer id);
}