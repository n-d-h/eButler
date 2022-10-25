package com.swp391.ebutler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swp391.ebutler.model.dto.ProductProviderDTO;
import com.swp391.ebutler.service.ProductProviderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/pprovider")
public class ProductProviderAPI {
	@Autowired
	ProductProviderService pproviderService;

	// List all product provider for customer
	@GetMapping("/list")
	public ResponseEntity<?> getListFoCus() {
		List<ProductProviderDTO> result = pproviderService.listAllFoCus();
		return ResponseEntity.ok(result);
	}

	// List all product provider for provider
	@GetMapping("/provider/list")
	public ResponseEntity<?> getList() {
		List<ProductProviderDTO> result = pproviderService.listAll();
		return ResponseEntity.ok(result);
	}

	// List product provider by productId
	@GetMapping("/listbyproduct/{id}")
	public ResponseEntity<?> getListByProductFoCus(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByProductIdFoCus(id);
		return ResponseEntity.ok(result);
	}

	// List product provider by productId for provider
	@GetMapping("/provider/listbyproduct/{id}")
	public ResponseEntity<?> getListByProduct(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByProductId(id);
		return ResponseEntity.ok(result);
	}

	// List product provider by providerId
	@GetMapping("/listbyprovider/{id}")
	public ResponseEntity<?> getListByProviderFoCus(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByProviderIdFoCus(id);
		return ResponseEntity.ok(result);
	}

	// List product provider by providerId for provider
	@GetMapping("/provider/listbyprovider/{id}")
	public ResponseEntity<?> getListByProvider(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByProviderId(id);
		return ResponseEntity.ok(result);
	}

	// Sort Param: sort = ? (Integer)
	// 0 - unitPrice - ASC
	// 1 - unitPrice - DESC
	// 2 - rating - ASC
	// 3 - rating - DESC
	@GetMapping("/list/sort/{pid}")
	public ResponseEntity<?> sortInt(@Param("sort") Integer sort, @PathVariable("pid") Integer pid) {
		List<ProductProviderDTO> result = pproviderService.sortInt(sort, pid);
		return ResponseEntity.ok(result);
	}

	// Add new ProductProvider
	@PostMapping("/list")
	public ResponseEntity<?> save(@Valid @RequestBody ProductProviderDTO pProvider) {
		return ResponseEntity.ok(pproviderService.save(pProvider));
	}

	// Update status to false
	@DeleteMapping("/list/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(pproviderService.delete(id));
	}

	// Update ProductProvider
	@PutMapping("/list/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody ProductProviderDTO pProvider) {
		pProvider.setProductproviderId(id);
		return ResponseEntity.ok(pproviderService.update(pProvider));
	}

	// List product provider by ManuId
	@GetMapping("/provider/listbymanu/{id}")
	public ResponseEntity<?> listByManuId(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByManuId(id);
		return ResponseEntity.ok(result);
	}

	// List product provider by CateId
	@GetMapping("/provider/listbycate/{id}")
	public ResponseEntity<?> listByCateId(@PathVariable("id") Integer id) {
		List<ProductProviderDTO> result = pproviderService.listByCateId(id);
		return ResponseEntity.ok(result);
	}

	// List product provider by id
	@GetMapping("/list/{id}")
	public ResponseEntity<?> listById(@PathVariable("id") Integer id) {
		ProductProviderDTO result = pproviderService.getByIdDTO(id);
		return ResponseEntity.ok(result);
	}
}
