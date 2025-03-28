package com.epf.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epf.core.model.Plante;
import com.epf.infrastructure.entities.PlanteEntity;

@Component
public class PlanteEntityMapper {

    public Plante mapEntityToModel(PlanteEntity entity) {
        if (entity == null) {
            return null;
        }

        Plante plante = new Plante();
        plante.setIdPlante(entity.getIdPlante());
        plante.setNom(entity.getNom());
        plante.setPointDeVie(entity.getPointDeVie());
        plante.setAttaqueParSeconde(entity.getAttaqueParSeconde());
        plante.setDegatAttaque(entity.getDegatAttaque());
        plante.setCout(entity.getCout());
        plante.setSoleilParSeconde(entity.getSoleilParSeconde());
        plante.setEffet(entity.getEffet());
        plante.setCheminImage(entity.getCheminImage());

        return plante;
    }

    public List<Plante> mapListEntityToListModel(List<PlanteEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public PlanteEntity mapModelToEntity(Plante plante) {
        if (plante == null) {
            return null;
        }

        PlanteEntity entity = new PlanteEntity();
        entity.setIdPlante(plante.getIdPlante());
        entity.setNom(plante.getNom());
        entity.setPointDeVie(plante.getPointDeVie());
        entity.setAttaqueParSeconde(plante.getAttaqueParSeconde());
        entity.setDegatAttaque(plante.getDegatAttaque());
        entity.setCout(plante.getCout());
        entity.setSoleilParSeconde(plante.getSoleilParSeconde());
        entity.setEffet(plante.getEffet());
        entity.setCheminImage(plante.getCheminImage());

        return entity;
    }
}
