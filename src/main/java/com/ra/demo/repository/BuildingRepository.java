package com.ra.demo.repository;

import com.ra.demo.model.constant.Status;
import com.ra.demo.model.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Page<Building> findByNameContaining(String name, Pageable pageable);
    Page<Building> findByStatusEquals(Status status, Pageable pageable);
}
