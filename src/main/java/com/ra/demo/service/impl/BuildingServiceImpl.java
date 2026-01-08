package com.ra.demo.service.impl;

import com.ra.demo.model.dto.request.BuildingDTO;
import com.ra.demo.model.dto.response.BuildingResponse;
import com.ra.demo.model.entity.Building;
import com.ra.demo.repository.BuildingRepository;
import com.ra.demo.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Page<BuildingResponse> findAll(Pageable pageable) {
        Page<Building> buildings = buildingRepository.findAll(pageable);
        return buildings.map(building ->
                BuildingResponse.builder()
                        .id(building.getId())
                        .name(building.getName())
                        .area(building.getArea())
                        .area_unit(building.getArea_unit())
                        .start_date(building.getStart_date())
                        .time(building.getTime())
                        .time_unit(building.getTime_unit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build());
    }

    @Override
    public BuildingResponse save(BuildingDTO buildingDTO) {
        Building building = Building.builder()
                .name(buildingDTO.getName())
                .area(buildingDTO.getArea())
                .area_unit(buildingDTO.getArea_unit())
                .start_date(buildingDTO.getStart_date())
                .time(buildingDTO.getTime())
                .time_unit(buildingDTO.getTime_unit())
                .design(buildingDTO.getDesign())
                .content(buildingDTO.getContent())
                .status(buildingDTO.getStatus())
                .build();

        Building newBuilding = buildingRepository.save(building);
        return BuildingResponse.builder()
                .id(newBuilding.getId())
                .name(newBuilding.getName())
                .area(newBuilding.getArea())
                .area_unit(newBuilding.getArea_unit())
                .start_date(newBuilding.getStart_date())
                .time(newBuilding.getTime())
                .time_unit(newBuilding.getTime_unit())
                .design(newBuilding.getDesign())
                .content(newBuilding.getContent())
                .status(newBuilding.getStatus())
                .build();
    }

    @Override
    public Page<BuildingResponse> searchBuildingByName(String name, Pageable pageable) {
        Page<Building> buildings = buildingRepository.findByName(name, pageable);
        return buildings.map(building ->
                BuildingResponse.builder()
                        .id(building.getId())
                        .name(building.getName())
                        .area(building.getArea())
                        .area_unit(building.getArea_unit())
                        .start_date(building.getStart_date())
                        .time(building.getTime())
                        .time_unit(building.getTime_unit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build()
        );
    }

    @Override
    public Building findById(long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        Building building = findById(id);

        if (building != null) {
            try {
                buildingRepository.delete(building);
                return new ResponseEntity<>("Delete sucessfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Building updateBuilding(int id, BuildingDTO buildingDTO) {
        Building oldBuilding = findById(id);

        if (oldBuilding != null) {
            Building newBuilding = new Building();
            newBuilding.setId(id);
            newBuilding.setName(buildingDTO.getName());
            newBuilding.setArea(buildingDTO.getArea());
            newBuilding.setArea_unit(buildingDTO.getArea_unit());
            newBuilding.setStart_date(buildingDTO.getStart_date());
            newBuilding.setTime(buildingDTO.getTime());
            newBuilding.setTime_unit(buildingDTO.getTime_unit());
            newBuilding.setDesign(buildingDTO.getDesign());
            newBuilding.setContent(buildingDTO.getContent());
            newBuilding.setStatus(buildingDTO.getStatus());

            try {
                return buildingRepository.save(newBuilding);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }
}
