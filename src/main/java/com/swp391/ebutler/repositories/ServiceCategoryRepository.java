package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp391.ebutler.entities.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {

	// find by name 
	List<ServiceCategory> findByServicecategoryNameContaining(String servicecategoryName);

	// find by status
	List<ServiceCategory> findByStatus(Boolean status);
	
	// find by name and status
	List<ServiceCategory> findByStatusAndServicecategoryNameContaining(Boolean status, String servicecategoryName);
	
}
