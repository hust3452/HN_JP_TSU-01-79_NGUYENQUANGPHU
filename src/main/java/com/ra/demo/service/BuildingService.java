package com.ra.demo.service;

import com.ra.demo.model.dto.request.BuildingDTO;
import com.ra.demo.model.dto.response.BuildingResponse;
import com.ra.demo.model.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BuildingService {
    Page<BuildingResponse> findAll(Pageable pageable);

    BuildingResponse save(BuildingDTO buildingDTO);

    Page<BuildingResponse> searchBuildingByName(String name, Pageable pageable);

    Building findById(long id);

    ResponseEntity<String> delete(int id);

    Building updateBuilding(int id, BuildingDTO buildingDTO);
}
