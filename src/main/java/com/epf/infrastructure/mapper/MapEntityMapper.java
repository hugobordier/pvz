
package com.epf.infrastructure.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epf.core.model.Map;
import com.epf.infrastructure.entities.MapEntity;

@Component
public class MapEntityMapper {

    public Map mapEntityToModel(MapEntity entity){
        if (entity == null) {
            return null;
        }

        Map map = new Map();
        map.setIdMap(entity.getIdMap());
        map.setCheminImage(entity.getCheminImage());
        map.setLigne(entity.getLigne());
        map.setColonne(entity.getColonne());

        return map;
    }

    public  List<Map> mapListEntityToListModel(List<MapEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToModel)
                .toList();
    }

    public MapEntity mapModelToEntity(Map map) {
        
        throw new UnsupportedOperationException("Unimplemented method 'mapModelToEntity'");
    }
}
