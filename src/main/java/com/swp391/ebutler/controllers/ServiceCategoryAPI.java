package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ServiceCategoryDTO;
import com.swp391.ebutler.service.ServiceCategoryService;

@RestController
@RequestMapping("/scategory")
public class ServiceCategoryAPI {

	@Autowired
	ServiceCategoryService scs;
	
	// Show all service categories
	@GetMapping("/list")
	public ResponseEntity<?> getList() {
		List<ServiceCategoryDTO> result = scs.listAll();
		return ResponseEntity.ok(result);
	}
	
	// Search service categories by name and sort ASC by name
	@GetMapping("/listbyname")
	public ResponseEntity<?> searchbyName(@Param("name") String name) {
		List<ServiceCategoryDTO> result = scs.searchByName(name);
		return ResponseEntity.ok(result);
	}
	
}
