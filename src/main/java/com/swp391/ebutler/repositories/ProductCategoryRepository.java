package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp391.ebutler.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
	List<ProductCategory> findByProductcategoryNameContaining(String productcategoryName);
	
	List<ProductCategory> findByStatus(Boolean status);
}
