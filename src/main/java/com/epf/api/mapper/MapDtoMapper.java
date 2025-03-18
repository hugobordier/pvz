package com.epf.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epf.api.dto.MapDto;
import com.epf.core.model.Map;

@Component
public class MapDtoMapper {
    
    public MapDto mapModelToDto(Map map) {
        if (map == null) {
            return null;
        }
        
        return new MapDto(
            map.getIdMap(),
            map.getLigne(),
            map.getColonne(),
            map.getCheminImage()
        );
    }
    
    public Map mapDtoToModel(MapDto mapDto) {
        if (mapDto == null) {
            return null;
        }
        
        Map map = new Map();
        map.setIdMap(mapDto.getId());
        map.setLigne(mapDto.getLigne());
        map.setColonne(mapDto.getColonne());
        map.setCheminImage(mapDto.getCheminImage());
        
        return map;
    }
    
    public List<MapDto> mapListModelToListDto(List<Map> maps) {
        return maps.stream()
                .map(this::mapModelToDto)
                .collect(Collectors.toList());
    }

    public List<Map> mapListDtoToListModel(List<MapDto> mapDtos) {
        return mapDtos.stream()
                .map(this::mapDtoToModel)
                .collect(Collectors.toList());
    }
}
