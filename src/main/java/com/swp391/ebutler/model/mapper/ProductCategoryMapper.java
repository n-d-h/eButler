package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.ProductCategory;
import com.swp391.ebutler.model.dto.ProductCategoryDTO;

public class ProductCategoryMapper {
	public static ProductCategoryDTO toProductCategoryDTO(ProductCategory proCategory) {
		ProductCategoryDTO procateDTO = new ProductCategoryDTO();
		procateDTO.setProductcategoryId(proCategory.getProductcategoryId());
		procateDTO.setProductcategoryName(proCategory.getProductcategoryName());
		procateDTO.setStatus(proCategory.getStatus());
		
		return procateDTO;
	}
}
