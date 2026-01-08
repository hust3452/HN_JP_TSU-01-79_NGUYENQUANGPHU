package com.ra.demo.repository;

import com.ra.demo.model.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Page<Building> findByName(String name, Pageable pageable);
}
