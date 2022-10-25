package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.Provider;
import com.swp391.ebutler.entities.ServiceProvider;
import com.swp391.ebutler.entities.Services;
import com.swp391.ebutler.model.dto.ServiceProviderDTO;
import com.swp391.ebutler.model.dto.SubServiceProviderDTO;
import com.swp391.ebutler.model.mapper.ServiceProviderMapper;
import com.swp391.ebutler.repositories.ProviderRepository;
import com.swp391.ebutler.repositories.ServiceProviderRepository;
import com.swp391.ebutler.repositories.ServicesRepository;
import com.swp391.ebutler.service.ServiceProviderService;

@Service
@Transactional
public class ServiceProviderServiceImp implements ServiceProviderService {

	@Autowired
	ServiceProviderRepository spRepo;
	@Autowired
	ServicesRepository sRepo;
	@Autowired
	ProviderRepository pRepo;

	// Show all
	@Override
	public List<ServiceProviderDTO> listAll() {
		List<ServiceProvider> result = spRepo.findAll();
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Show all by status true
	@Override
	public List<ServiceProviderDTO> listAllByStatus() {
		List<ServiceProvider> result = spRepo.findByStatus(true);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Show all by service id
	@Override
	public List<ServiceProviderDTO> listAllByServiceId(int id) {
		Services s = getServiceById(id);
		List<ServiceProvider> result = spRepo.findByService(s);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Show all by service id and status true
	@Override
	public List<ServiceProviderDTO> listAllByServiceIdAndStatus(int id) {
		Services s = getServiceById(id);
		List<ServiceProvider> result = spRepo.findByServiceAndStatus(s, true);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Show all by provider id
	@Override
	public List<ServiceProviderDTO> listAllByProviderId(int id) {
		Provider p = getProviderById(id);
		List<ServiceProvider> result = spRepo.findByProvider(p);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Show all by provider id and status true
	@Override
	public List<ServiceProviderDTO> listAllByProviderIdAndStatus(int id) {
		Provider p = getProviderById(id);
		List<ServiceProvider> result = spRepo.findByProviderAndStatus(p, true);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}
	
	
	// Save 
	@Override
	public SubServiceProviderDTO save(ServiceProviderDTO spDTO) {
		SubServiceProviderDTO sspDTO = null;
		Services s = getServiceById(spDTO.getServiceId());
		Provider p = getProviderById(spDTO.getProviderId());
		ServiceProvider sp = spRepo.findByProviderAndService(p, s);
		if (sp == null) {
			sp = toServiceProvider(spDTO);
			ServiceProviderMapper.toServiceProviderDTO(spRepo.save(sp));
			sspDTO = new SubServiceProviderDTO(-1, true);
		} else {
			sspDTO = new SubServiceProviderDTO(sp.getServiceproviderId(), sp.getStatus());
		}
		return sspDTO;
	}
	

	
	// Delete
	@Override
	public ServiceProviderDTO delete(int id) {
		ServiceProvider sp = getId(id);
		if (sp != null) {
			sp.setStatus(false);
			return ServiceProviderMapper.toServiceProviderDTO(spRepo.save(sp));
		}
		return null;
	}

	// Get By id
	public ServiceProvider getId(int id) {
		return spRepo.findById(id).get();
	}

	@Override
	public ServiceProviderDTO getById(int id) {
		return ServiceProviderMapper.toServiceProviderDTO(getId(id));
	}

	// Get list filter by service category id
	public List<ServiceProviderDTO> getByServicecategoryId(int id) {
		List<ServiceProvider> result = spRepo.findByServiceCategoryId(id);
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Count by service id
	@Override
	public Integer countByServiceId(int id) {
		return spRepo.countByServiceId(id);
	}

	// Count by provider id
	@Override
	public Integer countByProviderId(int id) {
		return spRepo.countByProviderId(id);
	}

	// sort
	@Override
	public List<ServiceProviderDTO> sort(int serviceId, int type) {
		String ASC = "ASC";
		String DESC = "DESC";
		switch (type) {
		case 1:
			return sortByPrice(serviceId, ASC);
		case 2:
			return sortByPrice(serviceId, DESC);
		case 3:
			return sortByRating(serviceId, ASC);
		case 4:
			return sortByRating(serviceId, DESC);
		default:
			return listAllByStatus();
		}
	}

	// sort by price
	@Override
	public List<ServiceProviderDTO> sortByPrice(int serviceId, String type) {
		List<ServiceProvider> result = null;
		if (type.equals("ASC")) {
			result = spRepo.sortAscByAvgPriceAndStatusTrue(serviceId);
		} else if (type.equals("DESC")) {
			result = spRepo.sortDescByAvgPriceAndStatusTrue(serviceId);
		} else {
			result = spRepo.findByStatus(true);
		}
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// sort by rating
	@Override
	public List<ServiceProviderDTO> sortByRating(int serviceId, String type) {
		List<ServiceProvider> result = null;
		Services s = getServiceById(serviceId);
		if (type.equals("ASC")) {
			result = spRepo.findByServiceAndStatus(s, true, Sort.by(Direction.ASC, "rating"));
		} else if (type.equals("DESC")) {
			result = spRepo.findByServiceAndStatus(s, true, Sort.by(Direction.DESC, "rating"));
		} else {
			result = spRepo.findByStatus(true);
		}
		List<ServiceProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ServiceProviderMapper.toServiceProviderDTO(v)));
		return listDTO;
	}

	// Type casting
	public ServiceProvider toServiceProvider(ServiceProviderDTO spDTO) {
		ServiceProvider sp = new ServiceProvider();
		sp.setServiceproviderId(spDTO.getServiceproviderId());
		sp.setRating(spDTO.getRating());
		sp.setService(getServiceById(spDTO.getServiceId()));
		sp.setProvider(getProviderById(spDTO.getProviderId()));
		sp.setPersonalDescription(spDTO.getPersonalDescription());
		sp.setMinPrice(spDTO.getMinPrice());
		sp.setMaxPrice(spDTO.getMaxPrice());
		sp.setStatus(spDTO.getStatus());
		return sp;
	}

	// get service by id
	public Services getServiceById(int id) {
		return sRepo.findById(id).get();
	}

	// get service provider by id
	public Provider getProviderById(int id) {
		return pRepo.findById(id).get();
	}

}
