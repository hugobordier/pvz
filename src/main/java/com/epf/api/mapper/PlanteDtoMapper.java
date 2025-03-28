package com.epf.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epf.api.dto.PlanteDto;
import com.epf.core.model.Plante;

@Component
public class PlanteDtoMapper {
    
    public PlanteDto mapModelToDto(Plante plante) {
        if (plante == null) {
            return null;
        }
        
        return new PlanteDto(
            plante.getIdPlante(),
            plante.getNom(),
            plante.getPointDeVie(),
            plante.getAttaqueParSeconde(),
            plante.getDegatAttaque(),
            plante.getCout(),
            plante.getSoleilParSeconde(),
            plante.getEffet(),
            plante.getCheminImage()
        );
    }
    
    public Plante mapDtoToModel(PlanteDto planteDto) {
        if (planteDto == null) {
            return null;
        }
        
        Plante plante = new Plante();
        plante.setIdPlante(planteDto.getId());
        plante.setNom(planteDto.getNom());
        plante.setPointDeVie(planteDto.getPointDeVie());
        plante.setAttaqueParSeconde(planteDto.getAttaqueParSeconde());
        plante.setDegatAttaque(planteDto.getDegatAttaque());
        plante.setCout(planteDto.getCout());
        plante.setSoleilParSeconde(planteDto.getSoleilParSeconde());
        plante.setEffet(planteDto.getEffet());
        plante.setCheminImage(planteDto.getCheminImage());
        
        return plante;
    }
    
    public List<PlanteDto> mapListModelToListDto(List<Plante> plantes) {
        return plantes.stream()
                .map(this::mapModelToDto)
                .collect(Collectors.toList());
    }

    public List<Plante> mapListDtoToListModel(List<PlanteDto> planteDtos) {
        return planteDtos.stream()
                .map(this::mapDtoToModel)
                .collect(Collectors.toList());
    }
}
