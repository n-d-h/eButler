package com.swp391.ebutler.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ServiceProviderDTO;
import com.swp391.ebutler.service.ServiceProviderService;

@RestController
@RequestMapping("/sprovider")
public class ServiceProviderAPI {
	
	@Autowired
	ServiceProviderService sps;
	
	// Show all service providers
	@GetMapping("/list")
	public ResponseEntity<?> getList() {
		List<ServiceProviderDTO> result = sps.listAll();
		return ResponseEntity.ok(result);
	}
	
}
