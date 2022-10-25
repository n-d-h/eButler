package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.model.dto.CustomerDTO;

public interface CustomerService {

	public List<CustomerDTO> listAll();
	
	public List<CustomerDTO> listAllActiveCustomerAccount();

	public CustomerDTO save(CustomerDTO c);

	public CustomerDTO getById(int id);
	
	public CustomerDTO getByEmail(String email);
	
	public CustomerDTO getByPhoneNumber(String phoneNumber);

	public List<CustomerDTO> searchByName(String name);
	
	// public List<CustomerDTO> searchActiveCustomerAccountByName(String name);
	
	public List<CustomerDTO> searchByAddress(String address);
	
	public List<CustomerDTO> searchByParam(String search);
}
