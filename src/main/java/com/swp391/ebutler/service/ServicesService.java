package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.model.dto.ServicesDTO;

public interface ServicesService {
	
	public List<ServicesDTO> listAll();
	
	public List<ServicesDTO> listAllByStatus();
	
	public List<ServicesDTO> listAllByCategoryId(int id);
	
	public List<ServicesDTO> listAllByCategoryIdAndStatus(int id);
	
	public ServicesDTO save(ServicesDTO s);
	
	public ServicesDTO delete(int id);
	
	public ServicesDTO getById(int id);
	
	public List<ServicesDTO> searchByServiceName(String name);
	
	public List<ServicesDTO> searchByServiceNameAndStatus(String name);
	
	public List<ServicesDTO> searchByProviderName(String name);
	
	public List<ServicesDTO> searchByProviderNameAndStatus(String name);
	
	public List<ServicesDTO> searchByParam(String search);
	
	public List<ServicesDTO> searchByParamAndStatus(String search);

}
