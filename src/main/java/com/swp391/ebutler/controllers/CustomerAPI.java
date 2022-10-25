
package com.swp391.ebutler.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.CustomerDTO;
import com.swp391.ebutler.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {

	@Autowired
	CustomerService cs;

	// Search By customer id
	@GetMapping("/info")
	public ResponseEntity<?> searchCustomerById(@Param("id") int id) {
		CustomerDTO result = cs.getById(id);
		return ResponseEntity.ok(result);
	}

	// Add a customer
	@PostMapping("/add")
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO c) {
		return ResponseEntity.ok(cs.save(c));
	}

	// Update a customer profile
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody CustomerDTO c) {
		c.setCustomerId(id);
		return ResponseEntity.ok(cs.save(c));
	}

}
