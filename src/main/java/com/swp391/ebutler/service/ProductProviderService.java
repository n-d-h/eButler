package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.ProductProvider;
import com.swp391.ebutler.model.dto.ProductProviderDTO;
import com.swp391.ebutler.model.dto.SubProductProviderDTO;

public interface ProductProviderService {
	public List<ProductProviderDTO> listAll();

	public List<ProductProviderDTO> listByProductId(Integer id);

	public List<ProductProviderDTO> listByProviderId(Integer id);

	public SubProductProviderDTO save(ProductProviderDTO pProvider);
	
	public ProductProviderDTO update(ProductProviderDTO pProvider);

	public ProductProviderDTO delete(Integer id);

	public ProductProviderDTO getByIdDTO(Integer id);

	public ProductProvider getById(Integer id);

	public Integer countByProductId(Integer id);

	public List<ProductProviderDTO> listAllFoCus();

	public List<ProductProviderDTO> listByProductIdFoCus(Integer id);

	public List<ProductProviderDTO> listByProviderIdFoCus(Integer id);

	public List<ProductProviderDTO> sortInt(Integer sort, Integer pid);
	
	public List<ProductProviderDTO> listByManuId(Integer id);
	
	public List<ProductProviderDTO> listByCateId(Integer id);
	
	public Integer getIdByDTO(ProductProviderDTO pProviderDTO);
}
