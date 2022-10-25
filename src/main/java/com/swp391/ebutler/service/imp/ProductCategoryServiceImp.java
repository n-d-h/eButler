package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.ProductCategory;
import com.swp391.ebutler.model.dto.ProductCategoryDTO;
import com.swp391.ebutler.model.mapper.ProductCategoryMapper;
import com.swp391.ebutler.repositories.ProductCategoryRepository;
import com.swp391.ebutler.service.ProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImp implements ProductCategoryService{

	@Autowired
	ProductCategoryRepository repo;
	
	@Override
	public List<ProductCategoryDTO> listAll(){
		List<ProductCategory> result = repo.findAll();
		List<ProductCategoryDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductCategoryMapper.toProductCategoryDTO(v)));
		return listDTO;
	}
	

	@Override
	public ProductCategoryDTO save(ProductCategoryDTO procateDTO) {
		ProductCategory procate = toProductCategory(procateDTO);
		return ProductCategoryMapper.toProductCategoryDTO(repo.save(procate));	
	}

	@Override
	public ProductCategoryDTO delete(Integer id) {
		ProductCategory proCategory = getById(id);
		if(proCategory != null) {
			proCategory.setStatus(false);
			return ProductCategoryMapper.toProductCategoryDTO(repo.save(proCategory));
		}
		return null;
	}

	@Override
	public ProductCategory getById(Integer id) {
		return repo.findById(id).get();
	}


	@Override
	public ProductCategoryDTO getByIdDTO(Integer id) {
		ProductCategory procate = repo.findById(id).get();
		if(procate != null) {
			return ProductCategoryMapper.toProductCategoryDTO(procate);
		}
		return null;
	}
	
	public ProductCategory toProductCategory(ProductCategoryDTO procateDTO) {
		ProductCategory procate = new ProductCategory();
		procate.setProductcategoryId(procateDTO.getProductcategoryId());
		procate.setProductcategoryName(procateDTO.getProductcategoryName());
		procate.setStatus(procateDTO.getStatus());
		return procate;
	}


	@Override
	public List<ProductCategoryDTO> searchByName(String name) {
		List<ProductCategory> result = repo.findByProductcategoryNameContaining(name);
		List<ProductCategoryDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductCategoryMapper.toProductCategoryDTO(v)));
		return listDTO;
	}


	@Override
	public List<ProductCategoryDTO> listAllFoCus() {
		List<ProductCategory> result = repo.findByStatus(true);
		List<ProductCategoryDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductCategoryMapper.toProductCategoryDTO(v)));
		return listDTO;
	}
	
}
