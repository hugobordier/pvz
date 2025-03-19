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
import com.epf.api.mapper.MapDtoMapper;
import com.epf.api.response.MapResponse;
import com.epf.core.model.Map;
import com.epf.core.service.MapService;

@RestController
@RequestMapping("/map")
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
    List<Map> maps = mapService.findAll();
    if (maps.isEmpty()) {
        return new ResponseEntity<>(new MapResponse(false, "Aucune map trouvée.", null), HttpStatus.NOT_FOUND);
    }
    
    List<MapDto> mapDtos = dtoMapper.mapListModelToListDto(maps);
    return new ResponseEntity<>(new MapResponse(true, "Maps récupérées avec succès.", mapDtos), HttpStatus.OK);
}
    
    @GetMapping("/{id}")
    public ResponseEntity<MapResponse> getMapById(@PathVariable("id") Integer id) {
        Map map = mapService.findById(id);
        if (map == null) {
            return new ResponseEntity<>(new MapResponse(false, "Map non trouvée.", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MapResponse(true, "Map trouvée avec succès.", dtoMapper.mapModelToDto(map)), HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<MapResponse> createMap(@RequestBody MapDto mapDto) {
    //     if (mapDto == null) {
    //         return ResponseEntity.badRequest().body(new MapResponse(false, "Les données envoyées sont invalides.", null));
    //     }

    //     Map map = dtoMapper.mapDtoToModel(mapDto);
    //     Map createdMap = mapService.createMap(map); 

    //     return ResponseEntity.status(HttpStatus.CREATED).body(new MapResponse(true, "Map créée avec succès.", dtoMapper.mapModelToDto(createdMap)));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<MapResponse> updateMap(@PathVariable("id") Integer id, @RequestBody MapDto mapDto) {
    //     Map existingMap = mapService.findById(id); 
    //     if (existingMap == null) {
    //         return new ResponseEntity<>(new MapResponse(false, "Map non trouvée.", null), HttpStatus.NOT_FOUND);
    //     }

    //     existingMap.setColonne(mapDto.getColonne());
    //     existingMap.setLigne(mapDto.getLigne());
    //     existingMap.setCheminImage(mapDto.getCheminImage());

    //     Map updatedMap = mapService.updateMap(existingMap);

    //     return new ResponseEntity<>(new MapResponse(true, "Map mise à jour avec succès.", dtoMapper.mapModelToDto(updatedMap)), HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<MapResponse> deleteMap(@PathVariable("id") Integer id) {
    //     Map existingMap = mapService.findById(id);
    //     if (existingMap == null) {
    //         return new ResponseEntity<>(new MapResponse(false, "Map non trouvée.", null), HttpStatus.NOT_FOUND);
    //     }

    //     mapService.delete(id);
    //     return new ResponseEntity<>(new MapResponse(true, "Map supprimée avec succès.", null), HttpStatus.NO_CONTENT);
    // }
}
