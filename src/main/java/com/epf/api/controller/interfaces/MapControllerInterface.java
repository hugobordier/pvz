package com.epf.api.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.epf.api.dto.MapDto;
import com.epf.api.response.MapResponse;

public interface MapControllerInterface {
    
    ResponseEntity<MapResponse> getAllMap();

    ResponseEntity<MapResponse> getMapById(Integer id);

    ResponseEntity<MapResponse> createMap(MapDto mapDto);

    ResponseEntity<MapResponse> updateMap(Integer id, MapDto mapDto);

    ResponseEntity<MapResponse> deleteMap(Integer id);
}
