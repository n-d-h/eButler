package com.swp391.ebutler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.service.OrderService;

@RestController
public class OrderAPI {
	
	@Autowired
	OrderService odsv;
	
	//Get all order of a customer
	@GetMapping("/api/order/listbycus/{id}")
	public ResponseEntity<?>getOrderByCus(@PathVariable("id") int id){
		return ResponseEntity.ok(odsv.getOrderByCus(id));
	}
	
	//Get all order of a provider
	@GetMapping("/api/order/listbyprovider/{id}")
	public ResponseEntity<?>getOrderByProvider(@PathVariable("id") int id){
		return ResponseEntity.ok(odsv.getOrderByProvider(id));
	}
	
	//Get all order of a product
	@GetMapping("/api/order/listbyproduct/{id}")
	public ResponseEntity<?>getOrderByProduct(@PathVariable("id") int id){
		return ResponseEntity.ok(odsv.getOrderByProduct(id));
	}
	
	//Change order status 
	@PostMapping("/api/order/changestatus")
	public ResponseEntity<?>changeOrderStatus(@RequestParam("id") int id, @RequestParam("status") int status){
		return ResponseEntity.ok(odsv.changeOrderStatus(id, status));
	}
	
	//Get list order have a specific status
	@GetMapping("/api/order/listbystatus/{id}")
	public ResponseEntity<?>getOrderByStatus(@PathVariable("id") int id){
		return ResponseEntity.ok(odsv.getOrderByStatus(id));
	}
}
