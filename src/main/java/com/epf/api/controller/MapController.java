package com.epf.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epf.api.dto.MapDto;
import com.epf.api.exception.ApiError;
import com.epf.api.mapper.MapDtoMapper;
import com.epf.api.response.MapResponse;
import com.epf.core.model.Map;
import com.epf.core.service.MapService;

@RestController
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;
    private final MapDtoMapper dtoMapper;

    @Autowired
    public MapController(MapService mapService, MapDtoMapper dtoMapper) {
        this.mapService = mapService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<MapResponse> getAllMap() {
        try {
            List<Map> maps = mapService.findAll();
            if (maps.isEmpty()) {
                throw new ApiError("Aucune map trouvée.", HttpStatus.NOT_FOUND);
            }
            
            List<MapDto> mapDtos = dtoMapper.mapListModelToListDto(maps);
            return new ResponseEntity<>(new MapResponse(true, "Maps récupérées avec succès.", mapDtos), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(new MapResponse(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new MapResponse(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MapResponse> getMapById(@PathVariable("id") Integer id) {
        try {
            Map map = mapService.findById(id);
            if (map == null) {
                throw new ApiError("Map non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new MapResponse(true, "Map trouvée avec succès.", dtoMapper.mapModelToDto(map)), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<>(new MapResponse(e.isSuccess(), e.getMessage(), null), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new MapResponse(false, "Erreur serveur: " + e.getMessage(), null), 
                                       HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping
    // public ResponseEntity<MapResponse> createMap(@RequestBody MapDto mapDto) {
    //     try {
    //         if (mapDto == null) {
    //             throw new ApiError("Les données envoyées sont invalides.", HttpStatus.BAD_REQUEST);
    //         }

    //         Map map = dtoMapper.mapDtoToModel(mapDto);
    //         Map createdMap = mapService.createdMap(map); 

    //         return ResponseEntity.status(HttpStatus.CREATED).body(new MapResponse(true, "Map créée avec succès.", dtoMapper.mapModelToDto(createdMap)));
    //     } catch (ApiError e) {
    //         return new ResponseEntity<>(new MapResponse(e.isSuccess(), e.getMessage(), null), e.getStatus());
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(new MapResponse(false, "Erreur serveur: " + e.getMessage(), null), 
    //                                    HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<MapResponse> updateMap(@PathVariable("id") Integer id, @RequestBody MapDto mapDto) {
    //     try {
    //         Map existingMap = mapService.findById(id); 
    //         if (existingMap == null) {
    //             throw new ApiError("Map non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
    //         }

    //         existingMap.setColonne(mapDto.getColonne());
    //         existingMap.setLigne(mapDto.getLigne());
    //         existingMap.setCheminImage(mapDto.getCheminImage());

    //         Map updatedMap = mapService.updateMap(existingMap);

    //         return new ResponseEntity<>(new MapResponse(true, "Map mise à jour avec succès.", dtoMapper.mapModelToDto(updatedMap)), HttpStatus.OK);
    //     } catch (ApiError e) {
    //         return new ResponseEntity<>(new MapResponse(e.isSuccess(), e.getMessage(), null), e.getStatus());
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(new MapResponse(false, "Erreur serveur: " + e.getMessage(), null), 
    //                                    HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<MapResponse> deleteMap(@PathVariable("id") Integer id) {
    //     try {
    //         Map existingMap = mapService.findById(id);
    //         if (existingMap == null) {
    //             throw new ApiError("Map non trouvée avec l'ID: " + id, HttpStatus.NOT_FOUND);
    //         }

    //         mapService.delete(id);
    //         return new ResponseEntity<>(new MapResponse(true, "Map supprimée avec succès.", null), HttpStatus.NO_CONTENT);
    //     } catch (ApiError e) {
    //         return new ResponseEntity<>(new MapResponse(e.isSuccess(), e.getMessage(), null), e.getStatus());
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(new MapResponse(false, "Erreur serveur: " + e.getMessage(), null), 
    //                                    HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}