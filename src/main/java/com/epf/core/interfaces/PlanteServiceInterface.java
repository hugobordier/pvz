package com.epf.core.interfaces;

import java.util.List;

import com.epf.core.model.Plante;

public interface PlanteServiceInterface {

    List<Plante> findAll();

    Plante findById(Integer id);

    Plante createPlante(Plante plante);

    Plante updatePlante(Plante updatedPlante);

    void delete(Integer id);
}
