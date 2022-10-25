package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.Manufacturer;
import com.swp391.ebutler.model.dto.ManufacturerDTO;
import com.swp391.ebutler.model.mapper.ManufacturerMapper;
import com.swp391.ebutler.repositories.ManufacturerRepository;
import com.swp391.ebutler.service.ManufacturerService;



@Service
@Transactional
public class ManufacturerServiceImp implements ManufacturerService{
	@Autowired
	ManufacturerRepository repo;
	
	@Override
	public List<ManufacturerDTO> listAll(){
		List<Manufacturer> result = repo.findAll();
		List<ManufacturerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ManufacturerMapper.toManufacturerDTO(v)));
		return listDTO;
	}

	@Override
	public ManufacturerDTO save(ManufacturerDTO manuDTO) {
		Manufacturer manu = toManufacturer(manuDTO);
		return ManufacturerMapper.toManufacturerDTO(repo.save(manu));	
	}

	@Override
	public ManufacturerDTO delete(Integer id) {
		Manufacturer manu = getById(id);
		if(manu != null) {
			manu.setStatus(false);
			return ManufacturerMapper.toManufacturerDTO(repo.save(manu));
		}
		return null;
	}
	
	@Override
	public Manufacturer getById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public ManufacturerDTO getByIdDTO(Integer id) {
		Manufacturer manu = repo.findById(id).get();
		if(manu != null ) {
			return ManufacturerMapper.toManufacturerDTO(manu);
		}
		return null;
	}
	
	public Manufacturer toManufacturer(ManufacturerDTO manuDTO) {
		Manufacturer manu = new Manufacturer();
		manu.setManufacturerId(manuDTO.getManufacturerId());
		manu.setManufacturerName(manuDTO.getManufacturerName());
		manu.setStatus(manuDTO.getStatus());
		return manu;
	}

	@Override
	public List<ManufacturerDTO> searchByName(String name) {
		List<Manufacturer> result = repo.findByManufacturerNameContaining(name);
		List<ManufacturerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ManufacturerMapper.toManufacturerDTO(v)));
		return listDTO;
	}

	@Override
	public List<ManufacturerDTO> listAllFoCus() {
		List<Manufacturer> result = repo.findByStatus(true);
		List<ManufacturerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ManufacturerMapper.toManufacturerDTO(v)));
		return listDTO;
	}
	
	

}
