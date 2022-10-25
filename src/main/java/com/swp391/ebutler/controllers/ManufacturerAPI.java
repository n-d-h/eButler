package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ManufacturerDTO;
import com.swp391.ebutler.service.ManufacturerService;



@RestController
@RequestMapping("/manufacturer")
public class ManufacturerAPI {
	@Autowired
	ManufacturerService manuService;
	
	//List all manufacturers
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<ManufacturerDTO> result = manuService.listAllFoCus();
		return ResponseEntity.ok(result);
	}
}
