package com.ra.demo.controller;

import com.ra.demo.model.constant.Status;
import com.ra.demo.model.dto.ResponseWrapper;
import com.ra.demo.model.dto.request.BuildingDTO;
import com.ra.demo.model.dto.response.BuildingResponse;
import com.ra.demo.model.entity.Building;
import com.ra.demo.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("api/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<?> getAllBuildings(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BuildingResponse> buildingDTO = buildingService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Get building successfully")
                        .dataResponse(buildingDTO)
                        .build());

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBuildings(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BuildingResponse> buildingResponseDTO = buildingService.searchBuildingByName(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Search building successfully")
                        .dataResponse(buildingResponseDTO)
                        .build());
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<?> searchBuildings(
            @RequestParam(name = "status", required = true) Status status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BuildingResponse> buildingResponseDTO = buildingService.searchBuildingByStatus(status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Search building successfully")
                        .dataResponse(buildingResponseDTO)
                        .build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBuilding(@Valid @ModelAttribute BuildingDTO buildingDTO) {
        BuildingResponse buildingResponse = buildingService.save(buildingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Add building successfully")
                        .build()
        );
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editBuilding(@PathVariable int id,
                                          @Valid @ModelAttribute BuildingDTO buildingDTO) {
        Building building = buildingService.updateBuilding(id, buildingDTO);
        if (building != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Update Fail",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable int id) {
        return new ResponseEntity<>(buildingService.delete(id),HttpStatus.OK);
    }
}
