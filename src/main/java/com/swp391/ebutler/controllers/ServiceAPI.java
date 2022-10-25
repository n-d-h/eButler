package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ServicesDTO;
import com.swp391.ebutler.service.ServicesService;

@RestController
@RequestMapping("/service")
public class ServiceAPI {

	@Autowired
	ServicesService ss;
	
	// Show all services
	@GetMapping("/service/list")
	public ResponseEntity<?> getListService() {
		List<ServicesDTO> result = ss.listAll();
		return ResponseEntity.ok(result);
	}
	
	// Search services by name 
	@GetMapping("/listbyname")
	public ResponseEntity<?> searchByName(@Param("name") String name) {
		List<ServicesDTO> result = ss.searchByServiceName(name);
		return ResponseEntity.ok(result);
	}
	
}
