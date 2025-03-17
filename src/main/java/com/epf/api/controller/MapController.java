package com.epf.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
