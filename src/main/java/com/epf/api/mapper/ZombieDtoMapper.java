package com.epf.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epf.api.dto.ZombieDto;
import com.epf.core.model.Zombie;

@Component
public class ZombieDtoMapper {

    public ZombieDto mapModelToDto(Zombie zombie) {
        if (zombie == null) {
            return null;
        }

        return new ZombieDto(
            zombie.getIdZombie(),
            zombie.getNom(),
            zombie.getPointDeVie(),
            zombie.getAttaqueParSeconde(),
            zombie.getDegatAttaque(),
            zombie.getVitesseDeDeplacement(),
            zombie.getCheminImage(),
            zombie.getIdMap()
        );
    }

    public Zombie mapDtoToModel(ZombieDto zombieDto) {
        if (zombieDto == null) {
            return null;
        }

        Zombie zombie = new Zombie();
        zombie.setIdZombie(zombieDto.getIdZombie());
        zombie.setNom(zombieDto.getNom());
        zombie.setPointDeVie(zombieDto.getPointDeVie());
        zombie.setAttaqueParSeconde(zombieDto.getAttaqueParSeconde());
        zombie.setDegatAttaque(zombieDto.getDegatAttaque());
        zombie.setVitesseDeDeplacement(zombieDto.getVitesseDeDeplacement());
        zombie.setCheminImage(zombieDto.getCheminImage());
        zombie.setIdMap(zombieDto.getIdMap());

        return zombie;
    }

    public List<ZombieDto> mapListModelToListDto(List<Zombie> zombies) {
        return zombies.stream()
                .map(this::mapModelToDto)
                .collect(Collectors.toList());
    }

    public List<Zombie> mapListDtoToListModel(List<ZombieDto> zombieDtos) {
        return zombieDtos.stream()
                .map(this::mapDtoToModel)
                .collect(Collectors.toList());
    }
}
