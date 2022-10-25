package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.Manufacturer;
import com.swp391.ebutler.model.dto.ManufacturerDTO;

public interface ManufacturerService {
	
	public List<ManufacturerDTO> listAll();
	
	public ManufacturerDTO save(ManufacturerDTO manu);
	
	public ManufacturerDTO delete(Integer id);
	
	public Manufacturer getById(Integer id);
	
	public ManufacturerDTO getByIdDTO(Integer id);
	
	public List<ManufacturerDTO> searchByName(String name);
	
	public List<ManufacturerDTO> listAllFoCus();
	
}
