package com.epf.api.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.epf.api.dto.PlanteDto;
import com.epf.api.response.PlanteResponse;

public interface PlanteControllerInterface {
    
    ResponseEntity<PlanteResponse> getAllPlantes();
    
    ResponseEntity<PlanteResponse> getPlanteById(Integer id);
    
    ResponseEntity<PlanteResponse> createPlante(PlanteDto planteDto);
    
    ResponseEntity<PlanteResponse> updatePlante(Integer id, PlanteDto planteDto);
    
    ResponseEntity<PlanteResponse> deletePlante(Integer id);
}
