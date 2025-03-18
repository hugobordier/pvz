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
    public List<MapDto> getAllMap() {
        List<Map> maps = mapService.findAll();
        return dtoMapper.mapListModelToListDto(maps);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MapDto> getMapById(@PathVariable("id") Integer id) {
        Map map = mapService.findById(id);
        if (map == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dtoMapper.mapModelToDto(map), HttpStatus.OK);
    }

//     public ResponseEntity<MapDto> createMap(@RequestBody MapDto mapDto) {
//     Map map = dtoMapper.mapDtoToModel(mapDto);  
//     Map createdMap = createMap(map); 
//     return new ResponseEntity<>(dtoMapper.mapModelToDto(createdMap), HttpStatus.CREATED);  
//     }

//     @PutMapping("/{id}")
// public ResponseEntity<MapDto> updateMap(@PathVariable("id") Integer id, @RequestBody MapDto mapDto) {
//     Map existingMap = mapService.findById(id); 
//     if (existingMap == null) {
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
//     }

//     existingMap.setColonne(mapDto.getColonne());
//     existingMap.setLigne(mapDto.getLigne());
//     existingMap.setCheminImage(mapDto.getCheminImage());

//     Map updatedMap = updateMap(existingMap);  
//     return new ResponseEntity<>(dtoMapper.mapModelToDto(updatedMap), HttpStatus.OK);  
// }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteMap(@PathVariable("id") Integer id) {
//         Map existingMap = mapService.findById(id);
//         if (existingMap == null) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }

//         mapService.delete(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
}
