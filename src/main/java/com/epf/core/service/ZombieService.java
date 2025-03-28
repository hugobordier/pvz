package com.epf.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epf.api.exception.ApiError;
import com.epf.core.model.Zombie;
import com.epf.infrastructure.repository.ZombieRepository;

@Service
public class ZombieService {

    private final ZombieRepository zombieRepository;

    @Autowired
    public ZombieService(ZombieRepository zombieRepository) {
        this.zombieRepository = zombieRepository;
    }

    
    public List<Zombie> findAll() {
        try {
            List<Zombie> zombies = zombieRepository.findAll();
            if (zombies.isEmpty()) {
                throw new ApiError("Aucun zombie trouvé.", HttpStatus.NOT_FOUND);
            }
            return zombies;
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération des zombies.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public Zombie findById(Integer id) {
        try {
            Zombie zombie = zombieRepository.findById(id);
            if (zombie == null) {
                throw new ApiError("Zombie introuvable.", HttpStatus.NOT_FOUND);
            }
            return zombie;
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la récupération du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public Zombie createZombie(Zombie zombie) {
        if (zombie == null) {
            throw new ApiError("Le zombie ne peut pas être nul.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getNom() == null || zombie.getNom().isEmpty()) {
            throw new ApiError("Le nom du zombie ne peut pas être vide.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getPointDeVie() == null || zombie.getPointDeVie() <= 0) {
            throw new ApiError("Le point de vie doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getAttaqueParSeconde() == null || zombie.getAttaqueParSeconde() <= 0) {
            throw new ApiError("L'attaque par seconde doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getDegatAttaque() == null || zombie.getDegatAttaque() <= 0) {
            throw new ApiError("Le dégât d'attaque doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getVitesseDeDeplacement() == null || zombie.getVitesseDeDeplacement() <= 0) {
            throw new ApiError("La vitesse de déplacement doit être un nombre positif.", HttpStatus.BAD_REQUEST);
        }

        if (zombie.getCheminImage() == null || zombie.getCheminImage().isEmpty()) {
            throw new ApiError("Le chemin de l'image ne peut pas être vide.", HttpStatus.BAD_REQUEST);
        }

        try {
            return zombieRepository.save(zombie);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la création du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public Zombie updateZombie(Zombie updatedZombie) {
        if (updatedZombie == null) {
            throw new ApiError("Le zombie ne peut pas être nul.", HttpStatus.BAD_REQUEST);
        }

        Zombie existingZombie = zombieRepository.findById(updatedZombie.getIdZombie());
        if (existingZombie == null) {
            throw new ApiError("Zombie introuvable.", HttpStatus.NOT_FOUND);
        }

        if (updatedZombie.getNom() != null && !updatedZombie.getNom().isEmpty()) {
            existingZombie.setNom(updatedZombie.getNom());
        }
        if (updatedZombie.getPointDeVie() != null && updatedZombie.getPointDeVie() > 0) {
            existingZombie.setPointDeVie(updatedZombie.getPointDeVie());
        }
        if (updatedZombie.getAttaqueParSeconde() != null && updatedZombie.getAttaqueParSeconde() > 0) {
            existingZombie.setAttaqueParSeconde(updatedZombie.getAttaqueParSeconde());
        }
        if (updatedZombie.getDegatAttaque() != null && updatedZombie.getDegatAttaque() > 0) {
            existingZombie.setDegatAttaque(updatedZombie.getDegatAttaque());
        }
        if (updatedZombie.getVitesseDeDeplacement() != null && updatedZombie.getVitesseDeDeplacement() > 0) {
            existingZombie.setVitesseDeDeplacement(updatedZombie.getVitesseDeDeplacement());
        }
        if (updatedZombie.getCheminImage() != null && !updatedZombie.getCheminImage().isEmpty()) {
            existingZombie.setCheminImage(updatedZombie.getCheminImage());
        }

        try {
            return zombieRepository.save(existingZombie);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la mise à jour du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public void delete(Integer id) {
        if (id == null) {
            throw new ApiError("L'ID ne peut pas être nul.", HttpStatus.BAD_REQUEST);
        }

        if (zombieRepository.findById(id) == null) {
            throw new ApiError("Zombie introuvable.", HttpStatus.NOT_FOUND);
        }

        try {
            zombieRepository.delete(id);
        } catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la suppression du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
