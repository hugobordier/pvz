package com.epf.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epf.api.dto.ZombieDto;
import com.epf.api.exception.ApiError;
import com.epf.api.mapper.ZombieDtoMapper;
import com.epf.api.response.ZombieResponse;
import com.epf.core.model.Zombie;
import com.epf.core.service.ZombieService;

@RestController
@RequestMapping("/zombies")
public class ZombieController {

    private final ZombieService zombieService;
    private final ZombieDtoMapper dtoMapper;

    @Autowired
    public ZombieController(ZombieService zombieService, ZombieDtoMapper dtoMapper) {
        this.zombieService = zombieService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<ZombieResponse> getAllZombies() {
        try {
            List<Zombie> zombies = zombieService.findAll();
            if (zombies.isEmpty()) {
                throw new ApiError("Aucun zombie trouvé.", HttpStatus.NOT_FOUND);
            }
            
            List<ZombieDto> zombieDtos = dtoMapper.mapListModelToListDto(zombies);
            return new ResponseEntity<>(new ZombieResponse(true, "Zombies récupérés avec succès.", zombieDtos), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(ZombieResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(ZombieResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ZombieResponse> getZombieById(@PathVariable("id") Integer id) {
        try {
            Zombie zombie = zombieService.findById(id);
            if (zombie == null) {
                throw new ApiError("Zombie non trouvé avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ZombieResponse.of(true, "Zombie trouvé avec succès.", dtoMapper.mapModelToDto(zombie)), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(ZombieResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(ZombieResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ZombieResponse> createZombie(@RequestBody ZombieDto zombieDto) {
        try {
            if (zombieDto == null) {
                throw new ApiError("Les données envoyées sont invalides.", HttpStatus.BAD_REQUEST);
            }

            Zombie zombie = dtoMapper.mapDtoToModel(zombieDto);
            Zombie createdZombie = zombieService.createZombie(zombie); 

            return ResponseEntity.status(HttpStatus.CREATED).body(ZombieResponse.of(true, "Zombie créé avec succès.", dtoMapper.mapModelToDto(createdZombie)));
        } catch (ApiError e) {
            return new ResponseEntity<>(ZombieResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(ZombieResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZombieResponse> updateZombie(@PathVariable("id") Integer id, @RequestBody ZombieDto zombieDto) {
        try {
            Zombie existingZombie = zombieService.findById(id); 
            if (existingZombie == null) {
                throw new ApiError("Zombie non trouvé avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }

            existingZombie.setNom(zombieDto.getNom());
            existingZombie.setPointDeVie(zombieDto.getPointDeVie());
            existingZombie.setAttaqueParSeconde(zombieDto.getAttaqueParSeconde());
            existingZombie.setDegatAttaque(zombieDto.getDegatAttaque());
            existingZombie.setVitesseDeDeplacement(zombieDto.getVitesseDeDeplacement());
            existingZombie.setCheminImage(zombieDto.getCheminImage());
            existingZombie.setIdMap(zombieDto.getIdMap());

            Zombie updatedZombie = zombieService.updateZombie(existingZombie);

            return new ResponseEntity<>(ZombieResponse.of(true, "Zombie mis à jour avec succès.", dtoMapper.mapModelToDto(updatedZombie)), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(ZombieResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(ZombieResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ZombieResponse> deleteZombie(@PathVariable("id") Integer id) {
        try {
            Zombie existingZombie = zombieService.findById(id);
            if (existingZombie == null) {
                throw new ApiError("Zombie non trouvé avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }

            zombieService.delete(id);
            return new ResponseEntity<>(ZombieResponse.of(true, "Zombie supprimé avec succès.", null), HttpStatus.NO_CONTENT);
        } catch (ApiError e) {
            return new ResponseEntity<>(ZombieResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(ZombieResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
