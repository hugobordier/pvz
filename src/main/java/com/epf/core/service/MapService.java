package com.epf.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epf.api.exception.ApiError;
import com.epf.core.interfaces.MapServiceInterface;
import com.epf.core.model.Map;
import com.epf.infrastructure.repository.MapRepository;

@Service
public class MapService implements MapServiceInterface {

    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Override
    public List<Map> findAll() {
        try {
            List<Map> maps = mapRepository.findAll();
            if (maps.isEmpty()) {
                throw new ApiError("Aucune map trouvée.", HttpStatus.NOT_FOUND);
            }
            return maps;
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération des maps.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map findById(Integer id) {
        try {
            return mapRepository.findById(id);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération de la map.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map createMap(Map map) {
        if (map == null) {
            throw new ApiError("La map ne peut pas être nulle.", HttpStatus.BAD_REQUEST);
        }

        if (map.getLigne() == null || map.getLigne() <= 0) {
            throw new ApiError("La ligne doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }
        if (map.getColonne() == null || map.getColonne() <= 0) {
            throw new ApiError("La colonne doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }
        if (map.getCheminImage() == null || map.getCheminImage().isEmpty()) {
            throw new ApiError("Le chemin de l'image ne peut pas être vide.", HttpStatus.BAD_REQUEST);
        }
        
        try {
            System.out.println("on rentre bien dans le service");
            return mapRepository.save(map);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la création de la map.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public Map updateMap(Map updatedMap) {
        if (updatedMap == null) {
            throw new ApiError("La map ne peut pas être nulle.", HttpStatus.BAD_REQUEST);
        }

        Map existingMap = mapRepository.findById(updatedMap.getIdMap());
        if (existingMap == null) {
            throw new ApiError("Map introuvable.", HttpStatus.NOT_FOUND);
        }

        if (updatedMap.getLigne() != null && updatedMap.getLigne() > 0) {
            existingMap.setLigne(updatedMap.getLigne());
        }
        if (updatedMap.getColonne() != null && updatedMap.getColonne() > 0) {
            existingMap.setColonne(updatedMap.getColonne());
        }
        if (updatedMap.getCheminImage() != null && !updatedMap.getCheminImage().isEmpty()) {
            existingMap.setCheminImage(updatedMap.getCheminImage());
        }

        try {
            return mapRepository.save(existingMap);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la mise à jour de la map.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public void delete(Integer id) {
    if (id == null) {
        throw new ApiError("L'ID ne peut pas être nul.", HttpStatus.BAD_REQUEST);
    }
    
    if (mapRepository.findById(id) == null) {
        throw new ApiError("Map introuvable.", HttpStatus.NOT_FOUND);
    }
    
    try {
        mapRepository.delete(id);
    } catch (DataAccessException e) {
        throw new ApiError("Erreur lors de la suppression de la map.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
