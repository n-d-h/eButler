package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.swp391.ebutler.entities.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
	
	List<Manufacturer> findByManufacturerNameContaining(String manufacturerName);
	
	List<Manufacturer> findByManufacturerNameContaining(String manufacturerName, Sort sort);
	
	List<Manufacturer> findByStatus(Boolean status);
}
