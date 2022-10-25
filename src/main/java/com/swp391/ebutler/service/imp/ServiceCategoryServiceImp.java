package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.ServiceCategory;
import com.swp391.ebutler.model.dto.ServiceCategoryDTO;
import com.swp391.ebutler.model.mapper.ServiceCategoryMapper;
import com.swp391.ebutler.repositories.ServiceCategoryRepository;
import com.swp391.ebutler.service.ServiceCategoryService;

@Service
@Transactional
public class ServiceCategoryServiceImp implements ServiceCategoryService {

	@Autowired
	ServiceCategoryRepository repo;

	// Show all 
	@Override
	public List<ServiceCategoryDTO> listAll() {
		List<ServiceCategory> result = repo.findAll();
		List<ServiceCategoryDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceCategoryMapper.toServiceCategoryDTO(v)));
		return listDTO;
	}
	
	// Show all by status true
	@Override
	public List<ServiceCategoryDTO> listAllByStatus() {
		List<ServiceCategory> result = repo.findByStatus(true);
		List<ServiceCategoryDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceCategoryMapper.toServiceCategoryDTO(v)));
		return listDTO;
	}

	// Save 
	@Override
	public ServiceCategoryDTO save(ServiceCategoryDTO scDTO) {
		ServiceCategory sc = toServiceCategory(scDTO);
		return ServiceCategoryMapper.toServiceCategoryDTO(repo.save(sc));
	}

	// Delete 
	@Override
	public ServiceCategoryDTO delete(int id) {
		ServiceCategory sc = getId(id);
		if (sc != null) {
			sc.setStatus(false);
			return ServiceCategoryMapper.toServiceCategoryDTO(repo.save(sc));
		}
		return null;
	}

	// Get by id
	public ServiceCategory getId(int id) {
		return repo.findById(id).get();
	}

	@Override
	public ServiceCategoryDTO getById(int id) {
		return ServiceCategoryMapper.toServiceCategoryDTO(getId(id));
	}
	
	// Search by name
	@Override
	public List<ServiceCategoryDTO> searchByName(String name) {
		List<ServiceCategory> result = repo.findByServicecategoryNameContaining(name);
		List<ServiceCategoryDTO> lisDtos = new ArrayList<>();
		result.forEach(v -> lisDtos.add(ServiceCategoryMapper.toServiceCategoryDTO(v)));
		return lisDtos;		
	}
	
	// Search by name and status
	@Override
	public List<ServiceCategoryDTO> searchByNameAndStatus(String name) {
		List<ServiceCategory> result = repo.findByStatusAndServicecategoryNameContaining(true, name);
		List<ServiceCategoryDTO> lisDtos = new ArrayList<>();
		result.forEach(v -> lisDtos.add(ServiceCategoryMapper.toServiceCategoryDTO(v)));
		return lisDtos;		
	}	
	
	// Type casting
	public ServiceCategory toServiceCategory(ServiceCategoryDTO scDTO) {
		ServiceCategory sc = new ServiceCategory();
		sc.setServicecategoryId(scDTO.getServicecategoryId());
		sc.setServicecategoryName(scDTO.getServicecategoryName());
		sc.setStatus(scDTO.getStatus());
		return sc;
		
	}
}
