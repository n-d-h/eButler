package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Product;
import com.swp391.ebutler.model.dto.ProductDTO;

public class ProductMapper {
	public static ProductDTO toProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setStatus(product.getStatus());
		productDTO.setManufacturerId(product.getManu().getManufacturerId());
		productDTO.setProductcategoryId(product.getProCategory().getProductcategoryId());
		return productDTO;
	}
}
