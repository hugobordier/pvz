package com.epf.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epf.core.model.Zombie;
import com.epf.infrastructure.entities.ZombieEntity;

@Component
public class ZombieEntityMapper {

    public Zombie mapEntityToModel(ZombieEntity entity) {
        if (entity == null) {
            return null;
        }

        Zombie zombie = new Zombie();
        zombie.setIdZombie(entity.getIdZombie());
        zombie.setNom(entity.getNom());
        zombie.setPointDeVie(entity.getPointDeVie());
        zombie.setAttaqueParSeconde(entity.getAttaqueParSeconde());
        zombie.setDegatAttaque(entity.getDegatAttaque());
        zombie.setVitesseDeDeplacement(entity.getVitesseDeDeplacement());
        zombie.setCheminImage(entity.getCheminImage());
        zombie.setIdMap(entity.getIdMap());

        return zombie;
    }

    public List<Zombie> mapListEntityToListModel(List<ZombieEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public ZombieEntity mapModelToEntity(Zombie zombie) {
        if (zombie == null) {
            return null;
        }

        ZombieEntity entity = new ZombieEntity();
        entity.setIdZombie(zombie.getIdZombie());
        entity.setNom(zombie.getNom());
        entity.setPointDeVie(zombie.getPointDeVie());
        entity.setAttaqueParSeconde(zombie.getAttaqueParSeconde());
        entity.setDegatAttaque(zombie.getDegatAttaque());
        entity.setVitesseDeDeplacement(zombie.getVitesseDeDeplacement());
        entity.setCheminImage(zombie.getCheminImage());
        entity.setIdMap(zombie.getIdMap());

        return entity;
    }
}
 
    

