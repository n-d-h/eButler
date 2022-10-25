package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.Account;
import com.swp391.ebutler.entities.Customer;
import com.swp391.ebutler.model.dto.CustomerDTO;
import com.swp391.ebutler.model.mapper.CustomerMapper;
import com.swp391.ebutler.repositories.AccountRepository;
import com.swp391.ebutler.repositories.CustomerRepository;
import com.swp391.ebutler.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerRepository cRepo;
	@Autowired
	AccountRepository aRepo;

	// Show all
	@Override
	public List<CustomerDTO> listAll() {
		List<Customer> result = cRepo.findAll();
		List<CustomerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(CustomerMapper.toCustomerDTO(v)));
		return listDTO;
	}

	
	// Show all active customer account
	@Override
	public List<CustomerDTO> listAllActiveCustomerAccount() {
		List<Customer> result = cRepo.listAllActiveCustomerAccount();
		List<CustomerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(CustomerMapper.toCustomerDTO(v)));
		return listDTO;
	}
	
	// Save
	@Override
	public CustomerDTO save(CustomerDTO cDTO) {
		Customer c = toCustomer(cDTO);
		return CustomerMapper.toCustomerDTO(cRepo.save(c));
	}

	/*---------------------------------------------SEARCH-------------------------------------------------------*/
	
	// Get by id
	@Override
	public CustomerDTO getById(int id) {
			return CustomerMapper.toCustomerDTO(cRepo.findById(id).get());
	}
	//---------------------------------------------------------------------------------------------------------
	
	// Search by Name 
	@Override
	public List<CustomerDTO> searchByName(String name) {
		List<Customer> result = cRepo.findByFullNameContaining(name);
		List<CustomerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(CustomerMapper.toCustomerDTO(v)));
		return listDTO;
	}

	//---------------------------------------------------------------------------------------------------------
	
	// Get by address
	@Override
	public List<CustomerDTO> searchByAddress(String address) {
		List<Customer> result = cRepo.findByAddressContaining(address);
		List<CustomerDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(CustomerMapper.toCustomerDTO(v)));
		return listDTO;
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	// Get by email
	@Override
	public CustomerDTO getByEmail(String email) {
		Customer c = cRepo.findByEmail(email);
		if (c != null) {
			return CustomerMapper.toCustomerDTO(c); 
		}
		return null;
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	// Get by phone number
	@Override
	public CustomerDTO getByPhoneNumber(String phoneNumber) {
		Customer c = cRepo.findByPhoneNumber(phoneNumber);
		if (c != null) {
			return CustomerMapper.toCustomerDTO(c); 
		}
		return null;
	}
	
	
	//---------------------------------------------------------------------------------------------------------
	
	// Search active customer account by name
	/*
	 * @Override public List<CustomerDTO> searchActiveCustomerAccountByName(String
	 * name) { List<Customer> result = cRepo.findActiveCustomerAccountByName(name);
	 * List<CustomerDTO> listDTO = new ArrayList<>(); result.forEach(v ->
	 * listDTO.add(CustomerMapper.toCustomerDTO(v))); return listDTO; }
	 */
	
	
	//---------------------------------------------------------------------------------------------------------
	
	/*
	// Checking search param is a numeric
	public Boolean isNumeric(String search) {
		try {
			Integer.parseInt(search);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	*/
	
	// Checking 10 digits phone number start with 0
	public Boolean isPhoneNumber(String search) {
		String phoneNumberPattern = "^[0][1-9][0-9]{8}$";
		return search.matches(phoneNumberPattern);
	}
	
	// Checking email
	public Boolean isEmail(String search) {
		String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		return search.matches(emailPattern);
	}

	//---------------------------------------------------------------------------------------------------------
	
	// Search by param [name, email, phoneNumber]
	public List<CustomerDTO> searchByParam(String search) {
		List<CustomerDTO> listDTO = new ArrayList<>();
		if (isPhoneNumber(search)) { 
			// search by phone number			
			listDTO.add(getByPhoneNumber(search));
			return listDTO;
		} else if (isEmail(search)) { 
			// search by email
			listDTO.add(getByEmail(search));
			return listDTO;
		} else { 
			// search by name
			return searchByName(search);
		}
	}

	/*---------------------------------------------END-SEARCH-------------------------------------------------------*/
	
	// Type casting
	public Customer toCustomer(CustomerDTO cDTO) {
		Customer c = new Customer();
		c.setCustomerId(cDTO.getCustomerId());
		c.setEmail(cDTO.getEmail());
		c.setFullName(cDTO.getFullName());
		c.setPhoneNumber(cDTO.getPhoneNumber());
		c.setAddress(cDTO.getAddress());
		c.setAccount(getAccountById(cDTO.getAccountId()));
		return c;
	}

	// Get account by id
	public Account getAccountById(int id) {
		return aRepo.findById(id).get();
	}
}
