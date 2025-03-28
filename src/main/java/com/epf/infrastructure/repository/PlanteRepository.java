package com.epf.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.core.model.Plante;
import com.epf.infrastructure.dao.PlanteDAO;
import com.epf.infrastructure.mapper.PlanteEntityMapper;

@Repository
public class PlanteRepository {
    
    private final PlanteDAO planteDAO;
    private final PlanteEntityMapper mapper;
    
    @Autowired
    public PlanteRepository(PlanteDAO planteDAO, PlanteEntityMapper mapper) {
        this.planteDAO = planteDAO;
        this.mapper = mapper;
    }
    
    public List<Plante> findAll() {
        return mapper.mapListEntityToListModel(planteDAO.findAll());
    }
    
    public Plante findById(Integer id) {
        return mapper.mapEntityToModel(planteDAO.findById(id));
    }

    public Plante save(Plante plante) {
        try {
            return planteDAO.save(plante);
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            planteDAO.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
