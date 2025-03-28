package com.epf.infrastructure.interfaces;

import java.util.List;

import com.epf.core.model.Map;
import com.epf.infrastructure.entities.MapEntity;

public interface MapDaoInterface {

    List<MapEntity> findAll();

    MapEntity findById(Integer id);

    Map save(Map map);

    void delete(Integer id);
}
