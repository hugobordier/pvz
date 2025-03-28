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

import com.epf.api.controller.interfaces.PlanteControllerInterface;
import com.epf.api.dto.PlanteDto;
import com.epf.api.exception.ApiError;
import com.epf.api.mapper.PlanteDtoMapper;
import com.epf.api.response.PlanteResponse;
import com.epf.core.model.Plante;
import com.epf.core.service.PlanteService;

@RestController
@RequestMapping("/plantes")
public class PlanteController implements PlanteControllerInterface {

    private final PlanteService planteService;
    private final PlanteDtoMapper dtoMapper;

    @Autowired
    public PlanteController(PlanteService planteService, PlanteDtoMapper dtoMapper) {
        this.planteService = planteService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @Override
    public ResponseEntity<PlanteResponse> getAllPlantes() {
        try {
            List<Plante> plantes = planteService.findAll();
            if (plantes.isEmpty()) {
                throw new ApiError("Aucune plante trouvée.", HttpStatus.NOT_FOUND);
            }

            List<PlanteDto> planteDtos = dtoMapper.mapListModelToListDto(plantes);
            return new ResponseEntity<>(new PlanteResponse(true, "Plantes récupérées avec succès.", planteDtos), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(PlanteResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(PlanteResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PlanteResponse> getPlanteById(@PathVariable("id") Integer id) {
        try {
            Plante plante = planteService.findById(id);
            if (plante == null) {
                throw new ApiError("Plante non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(PlanteResponse.of(true, "Plante trouvée avec succès.", dtoMapper.mapModelToDto(plante)), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(PlanteResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(PlanteResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<PlanteResponse> createPlante(@RequestBody PlanteDto planteDto) {
        try {
            if (planteDto == null) {
                throw new ApiError("Les données envoyées sont invalides.", HttpStatus.BAD_REQUEST);
            }

            Plante plante = dtoMapper.mapDtoToModel(planteDto);
            Plante createdPlante = planteService.createPlante(plante);

            return ResponseEntity.status(HttpStatus.CREATED).body(PlanteResponse.of(true, "Plante créée avec succès.", dtoMapper.mapModelToDto(createdPlante)));
        } catch (ApiError e) {
            return new ResponseEntity<>(PlanteResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(PlanteResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<PlanteResponse> updatePlante(@PathVariable("id") Integer id, @RequestBody PlanteDto planteDto) {
        try {
            Plante existingPlante = planteService.findById(id);
            if (existingPlante == null) {
                throw new ApiError("Plante non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }

            existingPlante.setNom(planteDto.getNom());
            existingPlante.setPointDeVie(planteDto.getPointDeVie());
            existingPlante.setAttaqueParSeconde(planteDto.getAttaqueParSeconde());
            existingPlante.setDegatAttaque(planteDto.getDegatAttaque());
            existingPlante.setCout(planteDto.getCout());
            existingPlante.setSoleilParSeconde(planteDto.getSoleilParSeconde());
            existingPlante.setEffet(planteDto.getEffet());
            existingPlante.setCheminImage(planteDto.getCheminImage());

            Plante updatedPlante = planteService.updatePlante(existingPlante);

            return new ResponseEntity<>(PlanteResponse.of(true, "Plante mise à jour avec succès.", dtoMapper.mapModelToDto(updatedPlante)), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(PlanteResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(PlanteResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<PlanteResponse> deletePlante(@PathVariable("id") Integer id) {
        try {
            Plante existingPlante = planteService.findById(id);
            if (existingPlante == null) {
                throw new ApiError("Plante non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }

            planteService.delete(id);
            return new ResponseEntity<>(PlanteResponse.of(true, "Plante supprimée avec succès.", null), HttpStatus.NO_CONTENT);
        } catch (ApiError e) {
            return new ResponseEntity<>(PlanteResponse.of(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(PlanteResponse.of(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
