package com.epf.infrastructure.interfaces;

import java.util.List;

import com.epf.core.model.Plante;
import com.epf.infrastructure.entities.PlanteEntity;

public interface PlanteDaoInterface {
    
    List<PlanteEntity> findAll();
    
    PlanteEntity findById(Integer id);
    
    Plante save(Plante plante);
    
    void delete(Integer id);
}