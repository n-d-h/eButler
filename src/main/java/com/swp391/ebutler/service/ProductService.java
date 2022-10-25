package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.Product;
import com.swp391.ebutler.model.dto.ProductDTO;


public interface ProductService {
	
	public List<ProductDTO> listAll();

	public ProductDTO save(ProductDTO product);

	public ProductDTO delete(Integer id);

	public Product getById(Integer id);
	
	public ProductDTO getByIdDTO(Integer id);

	public List<ProductDTO> searchByName(String name);
	
	public List<ProductDTO> listByCate(Integer cid);
	
	public List<ProductDTO> listByManu(Integer mid);
	
	public List<ProductDTO> listAllFoCus();
	
	public List<ProductDTO> searchByNameFoCus(String name);
	
	public List<ProductDTO> listByCateFoCus(Integer cid);
	
	public List<ProductDTO> listByManuFoCus(Integer mid);
	
}
