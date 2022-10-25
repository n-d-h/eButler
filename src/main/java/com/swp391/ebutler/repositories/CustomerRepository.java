package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	// find customer by name 
	List<Customer> findByFullNameContaining(String fullName);
	
	// find customer by address 
	List<Customer> findByAddressContaining(String address);
	
	// find customer by email
	Customer findByEmail(String email);
	
	// find customer by phone number
	Customer findByPhoneNumber(String phoneNumber);
	
	// find customer by id
	Customer findByCustomerId(Integer customerId);
	
	// find customer by account id
	@Query("SELECT c FROM Customer c Where c.account.accountId= :id")
	Customer findByAccountId(Integer id);
	
	// list all active customer account [status = true]
	@Query("SELECT c "
			+ "FROM Customer c INNER JOIN Account a "
			+ "ON c.account.accountId = a.accountId "
			+ "AND a.status = 1")
	List<Customer> listAllActiveCustomerAccount();
	
	// find active customer account by name
	/*
	 * @Query("SELECT c " + "FROM Customer c INNER JOIN Account a " +
	 * "ON c.account.accountId = a.accountId " + "AND a.status = 1" +
	 * "WHERE c.fullName like %?1%") List<Customer>
	 * findActiveCustomerAccountByName(String name);
	 */
	
}
