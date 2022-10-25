package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ProductCategoryDTO;
import com.swp391.ebutler.service.ProductCategoryService;

@RestController
@RequestMapping("/pcategory")
public class ProductCategoryAPI {
	@Autowired
	ProductCategoryService procateService;
	
	//List all Product Category
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<ProductCategoryDTO> result = procateService.listAllFoCus();
		return ResponseEntity.ok(result);
	}
	
}
