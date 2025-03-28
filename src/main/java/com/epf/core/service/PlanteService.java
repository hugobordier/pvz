package com.epf.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epf.api.exception.ApiError;
import com.epf.core.interfaces.PlanteServiceInterface;
import com.epf.core.model.Plante;
import com.epf.infrastructure.repository.PlanteRepository;

@Service
public class PlanteService implements PlanteServiceInterface {

    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    @Override
    public List<Plante> findAll() {
        try {
            List<Plante> plantes = planteRepository.findAll();
            if (plantes.isEmpty()) {
                throw new ApiError("Aucune plante trouvée.", HttpStatus.NOT_FOUND);
            }
            return plantes;
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération des plantes.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Plante findById(Integer id) {
        try {
            return planteRepository.findById(id);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération de la plante.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Plante createPlante(Plante plante) {
        if (plante == null) {
            throw new ApiError("La plante ne peut pas être nulle.", HttpStatus.BAD_REQUEST);
        }

        if (plante.getNom() == null || plante.getNom().isEmpty()) {
            throw new ApiError("Le nom de la plante ne peut pas être vide.", HttpStatus.BAD_REQUEST);
        }
        if (plante.getCout() == null || plante.getCout() <= 0) {
            throw new ApiError("Le coût de la plante doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }
        
        try {
            return planteRepository.save(plante);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la création de la plante.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Plante updatePlante(Plante updatedPlante) {
        if (updatedPlante == null) {
            throw new ApiError("La plante ne peut pas être nulle.", HttpStatus.BAD_REQUEST);
        }

        Plante existingPlante = planteRepository.findById(updatedPlante.getIdPlante());
        if (existingPlante == null) {
            throw new ApiError("Plante introuvable.", HttpStatus.NOT_FOUND);
        }

        if (updatedPlante.getNom() != null && !updatedPlante.getNom().isEmpty()) {
            existingPlante.setNom(updatedPlante.getNom());
        }
        if (updatedPlante.getCout() != null && updatedPlante.getCout() > 0) {
            existingPlante.setCout(updatedPlante.getCout());
        }

        try {
            return planteRepository.save(existingPlante);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la mise à jour de la plante.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new ApiError("L'ID ne peut pas être nul.", HttpStatus.BAD_REQUEST);
        }
        
        if (planteRepository.findById(id) == null) {
            throw new ApiError("Plante introuvable.", HttpStatus.NOT_FOUND);
        }
        
        try {
            planteRepository.delete(id);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la suppression de la plante.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
