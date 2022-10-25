package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp391.ebutler.entities.Manufacturer;
import com.swp391.ebutler.entities.Product;
import com.swp391.ebutler.entities.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByProductNameContaining(String productName);

	List<Product> findByProCategory(ProductCategory proCategory);

	List<Product> findByManu(Manufacturer manu);

	List<Product> findByStatus(Boolean status);

	List<Product> findByStatusAndProductNameContaining(Boolean status, String productName);

	List<Product> findByProCategoryAndStatus(ProductCategory proCategory, Boolean status);

	List<Product> findByManuAndStatus(Manufacturer manu, Boolean status);
	
}
