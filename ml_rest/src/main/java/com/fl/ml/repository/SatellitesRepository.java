package com.fl.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fl.ml.entity.SatelliteEntity;

public interface SatellitesRepository extends JpaRepository<SatelliteEntity, Long> {
	
	Boolean existsByName(String name);
}
