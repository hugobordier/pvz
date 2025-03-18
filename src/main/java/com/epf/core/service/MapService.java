package com.epf.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.core.model.Map;
import com.epf.infrastructure.repository.MapRepository;

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

    // public Map createMap(Map map) {
    // // Ajouter une logique de validation si nécessaire avant de sauvegarder
    // return mapRepository.save(map);  
    // }

    // public Map updateMap(Map map) {
    //     // Ajouter une logique de validation si nécessaire avant de mettre à jour
    //     // Par exemple, vérifier que la carte existe déjà dans la DB ou effectuer des transformations supplémentaires
    //     return mapRepository.save(map);  
    // }

}