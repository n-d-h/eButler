package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.ProductCategory;
import com.swp391.ebutler.model.dto.ProductCategoryDTO;


public interface ProductCategoryService {
	public List<ProductCategoryDTO> listAll();
	
	public ProductCategoryDTO save(ProductCategoryDTO procate);
	
	public ProductCategoryDTO delete(Integer id);
	
	public ProductCategory getById(Integer id);
	
	public ProductCategoryDTO getByIdDTO(Integer id);
	
	public List<ProductCategoryDTO> searchByName(String name);
	
	public List<ProductCategoryDTO> listAllFoCus();
}
