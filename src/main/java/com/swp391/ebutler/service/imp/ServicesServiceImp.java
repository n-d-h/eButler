package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.ServiceCategory;
import com.swp391.ebutler.entities.Services;
import com.swp391.ebutler.model.dto.ServicesDTO;
import com.swp391.ebutler.model.mapper.ServicesMapper;
import com.swp391.ebutler.repositories.ServiceCategoryRepository;
import com.swp391.ebutler.repositories.ServicesRepository;
import com.swp391.ebutler.service.ServicesService;

@Service
@Transactional
public class ServicesServiceImp implements ServicesService {

	@Autowired
	ServicesRepository sRepo;
	@Autowired
	ServiceCategoryRepository scRepo;

	// Show all
	@Override
	public List<ServicesDTO> listAll() {
		List<Services> result = sRepo.findAll();
		List<ServicesDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServicesMapper.toServicesDTO(v)));
		return listDTO;
	}

	// Show all by status true
	@Override
	public List<ServicesDTO> listAllByStatus() {
		List<Services> result = sRepo.findByStatus(true);
		List<ServicesDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServicesMapper.toServicesDTO(v)));
		return listDTO;
	}

	// Show all by category
	@Override
	public List<ServicesDTO> listAllByCategoryId(int id) {
		ServiceCategory cate = getServiceCategoryById(id);
		List<Services> result = sRepo.findBySerCategory(cate);
		List<ServicesDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServicesMapper.toServicesDTO(v)));
		return listDTO;
	}

	// Show all by category and status true
	@Override
	public List<ServicesDTO> listAllByCategoryIdAndStatus(int id) {
		ServiceCategory cate = getServiceCategoryById(id);
		List<Services> result = sRepo.findBySerCategoryAndStatus(cate, true);
		List<ServicesDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServicesMapper.toServicesDTO(v)));
		return listDTO;
	}

	// Save
	@Override
	public ServicesDTO save(ServicesDTO sDTO) {
		Services s = toServices(sDTO);
		return ServicesMapper.toServicesDTO(sRepo.save(s));
	}

	// Delete
	@Override
	public ServicesDTO delete(int id) {
		Services s = getId(id);
		if (s != null) {
			s.setStatus(false);
			return ServicesMapper.toServicesDTO(sRepo.save(s));
		}
		return null;
	}

	// Get by id
	public Services getId(int id) {
		return sRepo.findById(id).get();
	}

	@Override
	public ServicesDTO getById(int id) {
		return ServicesMapper.toServicesDTO(getId(id));
	}

	// Search by service name
	public List<ServicesDTO> searchByServiceName(String name) {
		List<Services> result = sRepo.findByServiceNameContaining(name);
		List<ServicesDTO> listDtos = new ArrayList<>();
		result.forEach(v -> listDtos.add(ServicesMapper.toServicesDTO(v)));
		return listDtos;
	}

	// Search by service name and status true
	@Override
	public List<ServicesDTO> searchByServiceNameAndStatus(String name) {
		List<Services> result = sRepo.findByStatusAndServiceNameContaining(true, name);
		List<ServicesDTO> listDtos = new ArrayList<>();
		result.forEach(v -> listDtos.add(ServicesMapper.toServicesDTO(v)));
		return listDtos;
	}

	// search by provider name
	@Override
	public List<ServicesDTO> searchByProviderName(String name) {
		List<Services> result = sRepo.findByProviderName(name);
		List<ServicesDTO> listDtos = new ArrayList<>();
		result.forEach(v -> listDtos.add(ServicesMapper.toServicesDTO(v)));
		return listDtos;
	}

	// search by provider name and status
	@Override
	public List<ServicesDTO> searchByProviderNameAndStatus(String name) {
		List<Services> result = sRepo.findByActiveProviderName(name);
		List<ServicesDTO> listDtos = new ArrayList<>();
		result.forEach(v -> listDtos.add(ServicesMapper.toServicesDTO(v)));
		return listDtos;
	}

	// Search by param [providerName || serviceName]
	public List<ServicesDTO> searchByParam(String search) {
		if (searchByServiceName(search).size() == 0) {
			return searchByProviderName(search);
		}
		return searchByServiceName(search);
	}

	// Search by param [providerName || serviceName] and status true
	public List<ServicesDTO> searchByParamAndStatus(String search) {
		if (searchByServiceNameAndStatus(search).size() == 0) {
			return searchByProviderNameAndStatus(search);
		} else
			return searchByServiceNameAndStatus(search);
	}

	// Type casting
	public Services toServices(ServicesDTO sDTO) {
		Services s = new Services();
		s.setServiceId(sDTO.getServiceId());
		s.setServiceName(sDTO.getServiceName());
		s.setDescription(sDTO.getDescription());
		s.setImage(sDTO.getImage());
		s.setStatus(sDTO.getStatus());
		s.setSerCategory(getServiceCategoryById(sDTO.getServicecategoryId()));
		return s;
	}

	// Get service category by id
	public ServiceCategory getServiceCategoryById(int id) {
		return scRepo.findById(id).get();
	}

}
