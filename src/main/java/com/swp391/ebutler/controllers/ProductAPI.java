package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.ebutler.model.dto.ProductDTO;
import com.swp391.ebutler.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductAPI {
	@Autowired
	ProductService pService;

	// List all products
	@GetMapping("/list")
	public ResponseEntity<?> getList() {
		List<ProductDTO> result = pService.listAllFoCus();
		return ResponseEntity.ok(result);
	}

	// List products by name
	@GetMapping("/list/getbyname")
	public ResponseEntity<?> getListByName(@Param("name") String name) {
		List<ProductDTO> result = pService.searchByNameFoCus(name);
		return ResponseEntity.ok(result);
	}

	// List products by {category id}
	@GetMapping("/list/getbycategory/{cid}")
	public ResponseEntity<?> getListByCate(@PathVariable("cid") Integer cid) {
		List<ProductDTO> result = pService.listByCateFoCus(cid);
		return ResponseEntity.ok(result);
	}

	// List products by {manufacturer id}
	@GetMapping("/list/getbymanu/{mid}")
	public ResponseEntity<?> getListByManu(@PathVariable("mid") Integer mid) {
		List<ProductDTO> result = pService.listByManuFoCus(mid);
		return ResponseEntity.ok(result);
	}

	// Add new product to db
	@PostMapping("/list")
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO product) {
		return ResponseEntity.ok(pService.save(product));
	}

	// Set status product to false
	@DeleteMapping("/list/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(pService.delete(id));
	}

	// Update product
	@PutMapping("/list/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id,@Valid @RequestBody ProductDTO product) {
		product.setProductId(id);
		return ResponseEntity.ok(pService.save(product));
	}

	// List products by {manufacturer id}
	@GetMapping("/list/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(pService.getByIdDTO(id));
	}
}
