package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Customer;
import com.swp391.ebutler.model.dto.CustomerDTO;

public class CustomerMapper {
	public static CustomerDTO toCustomerDTO(Customer cus) {
		CustomerDTO cusDTO = new CustomerDTO();
		cusDTO.setAccountId(cus.getAccount().getAccountId());
		cusDTO.setEmail(cus.getEmail());
		cusDTO.setFullName(cus.getFullName());
		cusDTO.setPhoneNumber(cus.getPhoneNumber());
		cusDTO.setAddress(cus.getAddress());
		cusDTO.setCustomerId(cus.getCustomerId());
		return cusDTO;
	}
}
