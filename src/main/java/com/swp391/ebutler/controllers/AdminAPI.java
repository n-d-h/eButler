package com.swp391.ebutler.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.swp391.ebutler.model.dto.CustomerDTO;
import com.swp391.ebutler.model.dto.ManufacturerDTO;
import com.swp391.ebutler.model.dto.ProductCategoryDTO;
import com.swp391.ebutler.model.dto.ProductDTO;
import com.swp391.ebutler.model.dto.ServiceCategoryDTO;
import com.swp391.ebutler.model.dto.ServiceProviderDTO;
import com.swp391.ebutler.model.dto.ServicesDTO;
import com.swp391.ebutler.service.CustomerService;
import com.swp391.ebutler.service.ManufacturerService;
import com.swp391.ebutler.service.ProductCategoryService;
import com.swp391.ebutler.service.ProductProviderService;
import com.swp391.ebutler.service.ProductService;
import com.swp391.ebutler.service.ServiceCategoryService;
import com.swp391.ebutler.service.ServiceProviderService;
import com.swp391.ebutler.service.ServicesService;

@RestController
@RequestMapping("/api/admin")
public class AdminAPI {

	@Autowired
    ManufacturerService manuService;
    @Autowired
    ProductService pService;
    @Autowired
    ProductCategoryService procateService;
    @Autowired
    ProductProviderService pproviderService;
    @Autowired
    CustomerService cs;
    @Autowired
    ServicesService ss;
    @Autowired
    ServiceCategoryService scs;
    @Autowired
    ServiceProviderService sps;
    
	// List all manufacturers
	@GetMapping("/manufacturer/list")
	public ResponseEntity<?> getListManu() {
		List<ManufacturerDTO> result = manuService.listAll();
		return ResponseEntity.ok(result);
	}

	// List manufacturers by name
	@GetMapping("/manufacturer/listbyname")
	public ResponseEntity<?> getListManuByName(@Param("name") String name) {
		List<ManufacturerDTO> result = manuService.searchByName(name);
		return ResponseEntity.ok(result);
	}

	// Add new manufacturer
	@PostMapping("/manufacturer/save")
	public ResponseEntity<?> saveManu(@Valid @RequestBody ManufacturerDTO manu) {
		return ResponseEntity.ok(manuService.save(manu));
	}

	// Update manufacturer status to false
	@DeleteMapping("/manufacturer/delete/{id}")
	public ResponseEntity<?> deleteManu(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(manuService.delete(id));
	}

	// Update manufacturer
	@PutMapping("/manufacturer/update/{id}")
	public ResponseEntity<?> updateManu(@PathVariable("id") Integer id,@Valid @RequestBody ManufacturerDTO manu) {
		manu.setManufacturerId(id);
		return ResponseEntity.ok(manuService.save(manu));
	}
	
	//List all product category
	@GetMapping("/pcategory/list")
	public ResponseEntity<?> getList(){
		List<ProductCategoryDTO> result = procateService.listAll();
		return ResponseEntity.ok(result);
	}
	
	//List procate by name
	@GetMapping("/pcategory/listbyname")
	public ResponseEntity<?> getListByName(@Param("name") String name){
		List<ProductCategoryDTO> result = procateService.searchByName(name);
		return ResponseEntity.ok(result);
	}
	
	//Add new procate
	@PostMapping("/pcategory/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductCategoryDTO proCategory){
		return ResponseEntity.ok(procateService.save(proCategory));
	}
	
	//Update status procate to false
	@DeleteMapping("/pcategory/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		return ResponseEntity.ok(procateService.delete(id));
	}
	
	//Update procate
	@PutMapping("/pcategory/update/{id}")
	public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id,@Valid @RequestBody ProductCategoryDTO proCategory) {
		proCategory.setProductcategoryId(id);
		return ResponseEntity.ok(procateService.save(proCategory));
	}	
	
	// List all products
	@GetMapping("/product/list")
	public ResponseEntity<?> getListProduct() {
		List<ProductDTO> result = pService.listAll();
		return ResponseEntity.ok(result);
	}

	//Get product by ID
	@GetMapping("/product/getbyid/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(pService.getByIdDTO(id));
	}

	// List products by name
	@GetMapping("/product/listbyname")
	public ResponseEntity<?> getListProductByName(@Param("name") String name) {
		List<ProductDTO> result = pService.searchByName(name);
		return ResponseEntity.ok(result);
	}

	//Add new product
	@PostMapping("/product/save")
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDTO product){
		return ResponseEntity.ok(pService.save(product));
	}

	//Set status product to false
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
		return ResponseEntity.ok(pService.delete(id));
	}
	
	//Update product
	@PutMapping("/product/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id,@Valid @RequestBody ProductDTO product) {
		product.setProductId(id);
		return ResponseEntity.ok(pService.save(product));
	}
	
	//
	//Count provider by product_provider
	@GetMapping("/product/countprovider/{id}")
	public ResponseEntity<?> countProductProvider(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(pproviderService.countByProductId(id));
	}
	
	// Show all customers
	@GetMapping("/customer/list")
	public ResponseEntity<?> getListCustomer() {
		List<CustomerDTO> result = cs.listAll();
		return ResponseEntity.ok(result);
	}
	
	// Show all ACTIVE customer [status true]
	@GetMapping("/customer/active/list")
	public ResponseEntity<?> listAllActiveCustomer() {
		List<CustomerDTO> result = cs.listAllActiveCustomerAccount();
		return ResponseEntity.ok(result);
	}

	// Add a customer
	@PostMapping("/customer/add")
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO c) {
		return ResponseEntity.ok(cs.save(c));
	}

	// Update a customer profile
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody CustomerDTO c) {
		c.setCustomerId(id);
		return ResponseEntity.ok(cs.save(c));
	}
	
	// Search by customer address
	@GetMapping("/customer/address/search")
	public ResponseEntity<?> listCustomerByAddress(@Param("address") String address) {
		List<CustomerDTO> result = cs.searchByAddress(address);
		return ResponseEntity.ok(result);
	}
	
	// get customer by id
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> searchCustomerById(@PathVariable("id") int id) {
		CustomerDTO result = cs.getById(id);
		return ResponseEntity.ok(result);
	}
	
	// Search by parameters (name/email/phoneNumber) 
	@GetMapping("/customer/search")
	public ResponseEntity<?> searchCustomer(@Param("search") String search) {
		List<CustomerDTO> result = cs.searchByParam(search);
		return ResponseEntity.ok(result);
	}
	
/*-----------------------------------------------------------------------------------------------------------------------------*/
	/* SERVICE */
	
	// Show all services
	@GetMapping("/service/list")
	public ResponseEntity<?> getListService() {
		List<ServicesDTO> result = ss.listAll();
		return ResponseEntity.ok(result);
	}
	
	// Show all ACTIVE services [status true]
	@GetMapping("/service/active/list")
	public ResponseEntity<?> getListActiveService() {
		List<ServicesDTO> result = ss.listAllByStatus();
		return ResponseEntity.ok(result);
	}
	
	// Show all services by category
	@GetMapping("/service/listbycategory/{id}")
	public ResponseEntity<?> getListServiceByCategory(@PathVariable("id") int id) {
		List<ServicesDTO> result = ss.listAllByCategoryId(id);
		return ResponseEntity.ok(result);
	}	
	
	// Show all ACTIVE services by category [status true]
	@GetMapping("/service/active/listbycategory/{id}")
	public ResponseEntity<?> getListActiveServiceByCategory(@PathVariable("id") int id) {
		List<ServicesDTO> result = ss.listAllByCategoryIdAndStatus(id);
		return ResponseEntity.ok(result);
	}
	
	// Add a services
	@PostMapping("/service/add")
	public ResponseEntity<?> saveService(@Valid @RequestBody ServicesDTO s) {
		return ResponseEntity.ok(ss.save(s));
	}

	// Delete a service
	@DeleteMapping("/service/delete/{id}")
	public ResponseEntity<?> deleteService(@PathVariable("id") int id) {
		return ResponseEntity.ok(ss.delete(id));
	}

	// Update a service
	@PutMapping("/service/update/{id}")
	public ResponseEntity<?> updateService(@PathVariable("id") int id, @Valid @RequestBody ServicesDTO s) {
		s.setServiceId(id);
		return ResponseEntity.ok(ss.save(s));
	}

	// Search services by param [provider name, service name] 
	@GetMapping("/service/search")
	public ResponseEntity<?> searchServiceByName(@Param("search") String search) {
		List<ServicesDTO> result = ss.searchByParam(search);
		return ResponseEntity.ok(result);
	}

	// Search ACTIVE services by param [provider name, service name] [status true]
	@GetMapping("/service/active/search")
	public ResponseEntity<?> searchActiveServiceByName(@Param("search") String search) {
		List<ServicesDTO> result = ss.searchByParamAndStatus(search);
		return ResponseEntity.ok(result);
	}
	
	// Get service by id
	@GetMapping("/service/{id}")
	public ResponseEntity<?> searchServiceById(@PathVariable("id") int id) {
		ServicesDTO result = ss.getById(id);
		return ResponseEntity.ok(result);
	}
	
/*-----------------------------------------------------------------------------------------------------------------------------*/
	/* SERVICE CATEGORY */
	
	// Show all service categories
	@GetMapping("/scategory/list")
	public ResponseEntity<?> getListServiceCategory() {
		List<ServiceCategoryDTO> result = scs.listAll();
		return ResponseEntity.ok(result);
	}
	
	// Show all ACTIVE service category [status true]
	@GetMapping("/scategory/active/list")
	public ResponseEntity<?> getListActiveServiceCategory() {
		List<ServiceCategoryDTO> result = scs.listAllByStatus();
		return ResponseEntity.ok(result);
	}

	// Add a service category
	@PostMapping("/scategory/add")
	public ResponseEntity<?> saveServiceCategory(@Valid @RequestBody ServiceCategoryDTO sc) {
		return ResponseEntity.ok(scs.save(sc));
	}

	// Delete a service category
	@DeleteMapping("/scategory/delete/{id}")
	public ResponseEntity<?> deleteServiceCategory(@PathVariable("id") int id) {
		return ResponseEntity.ok(scs.delete(id));
	}

	// Update a service category
	@PutMapping("/scategory/update/{id}")
	public ResponseEntity<?> updateServiceCategory(@PathVariable("id") int id, @Valid @RequestBody ServiceCategoryDTO sc) {
		sc.setServicecategoryId(id);
		return ResponseEntity.ok(scs.save(sc));
	}

	// Search service categories by name
	@GetMapping("/scategory/search")
	public ResponseEntity<?> searchServiceCategorybyName(@Param("name") String name) {
		List<ServiceCategoryDTO> result = scs.searchByName(name);
		return ResponseEntity.ok(result);
	}

	// Search ACTIVE service categories by name [status true]
	@GetMapping("/scategory/active/search")
	public ResponseEntity<?> searchActiveServiceCategorybyName(@Param("name") String name) {
		List<ServiceCategoryDTO> result = scs.searchByNameAndStatus(name);
		return ResponseEntity.ok(result);
	}
	
	// Get service category by id
	@GetMapping("/scategory/{id}")
	public ResponseEntity<?> searchServiceCategorybyId(@PathVariable("id") int id) {
		ServiceCategoryDTO result = scs.getById(id);
		return ResponseEntity.ok(result);
	}
	
/*-----------------------------------------------------------------------------------------------------------------------------*/
	/* SERVICE PROVIDER */
	
	// Show all service providers
	@GetMapping("/sprovider/list")
	public ResponseEntity<?> getListServiceProvider() {
		List<ServiceProviderDTO> result = sps.listAll();
		return ResponseEntity.ok(result);
	}

	// Show all ACTIVE service providers [status true]
	@GetMapping("/sprovider/active/list")
	public ResponseEntity<?> getListActiveServiceProvider() {
		List<ServiceProviderDTO> result = sps.listAllByStatus();
		return ResponseEntity.ok(result);
	}
	
	// Show all service providers by service
	@GetMapping("/sprovider/listbyservice/{id}")
	public ResponseEntity<?> getListServiceProviderByService(@PathVariable("id") int id) {
		List<ServiceProviderDTO> result = sps.listAllByServiceId(id);
		return ResponseEntity.ok(result);
	}
	
	// Show all ACTIVE service providers by service [status true]
	@GetMapping("/sprovider/active/listbyservice/{id}")
	public ResponseEntity<?> getListActiveServiceProviderByService(@PathVariable("id") int id) {
		List<ServiceProviderDTO> result = sps.listAllByServiceIdAndStatus(id);
		return ResponseEntity.ok(result);
	}
	
	// Show all service providers by provider
	@GetMapping("/sprovider/listbyprovider/{id}")
	public ResponseEntity<?> getListServiceProviderByProvider(@PathVariable("id") int id) {
		List<ServiceProviderDTO> result = sps.listAllByProviderId(id);
		return ResponseEntity.ok(result);
	}

	// Show all ACTIVE service providers by provider [status true]
	@GetMapping("/sprovider/active/listbyprovider/{id}")
	public ResponseEntity<?> getListActiveServiceProviderByProvider(@PathVariable("id") int id) {
		List<ServiceProviderDTO> result = sps.listAllByProviderIdAndStatus(id);
		return ResponseEntity.ok(result);
	}
		
	// Add a service provider
	@PostMapping("/sprovider/add")
	public ResponseEntity<?> saveServiceProvider(@Valid @RequestBody ServiceProviderDTO sp) {
		return ResponseEntity.ok(sps.save(sp));
	}

	// Delete a service provider
	@DeleteMapping("/sprovider/delete/{id}")
	public ResponseEntity<?> deleteServiceProvider(@PathVariable("id") int id) {
		return ResponseEntity.ok(sps.delete(id));
	}

	// Update a service provider
	@PutMapping("/sprovider/update/{id}")
	public ResponseEntity<?> updateServiceProvider(@PathVariable("id") int id, @Valid @RequestBody ServiceProviderDTO sp) {
		sp.setServiceproviderId(id);
		return ResponseEntity.ok(sps.save(sp));
	}
	
	// Count providers by service providers 
	@GetMapping("/sprovider/countprovider/{id}")
	public ResponseEntity<?> countProviderByServiceProvider(@PathVariable("id") int id) {
		return ResponseEntity.ok(sps.countByServiceId(id));
	}
	
	// Count services of one provider by service providers
	@GetMapping("/sprovider/countservice/{id}")
	public ResponseEntity<?> countServiceByServiceProvider(@PathVariable("id") int id) {
		return ResponseEntity.ok(sps.countByProviderId(id));
	}
	
	// Sort service providers by param [price, rating] order by [type] ASC or DESC  [int type = {1, 2, 3, 4}]
	@GetMapping("sprovider/list/sort/{serviceId}")
	public ResponseEntity<?> sortListServiceProvider(@PathVariable("serviceId") int serviceId, @Param("type") int type) {
		List<ServiceProviderDTO> result = sps.sort(serviceId, type);
		return ResponseEntity.ok(result);
	}
	
	// Get List Filter by service category 
	@GetMapping("sprovider/getbycategory/{id}")//
	public ResponseEntity<?> getListFilterByCategory(@PathVariable("id") int id) {
		List<ServiceProviderDTO> result = sps.getByServicecategoryId(id);
		return ResponseEntity.ok(result);
	}
	
	// Get service provider by id
	@GetMapping("/sprovider/{id}")
	public ResponseEntity<?> searchServiceProviderById(@PathVariable("id") int id) {
		ServiceProviderDTO result = sps.getById(id);
		return ResponseEntity.ok(result);
	}

	// List products by {category id}
	@GetMapping("/product/getbycategory/{cid}")
	public ResponseEntity<?> getListByCate(@PathVariable("cid") Integer cid) {
		List<ProductDTO> result = pService.listByCate(cid);
		return ResponseEntity.ok(result);
	}

	// List products by {manufacturer id}
	@GetMapping("/product/getbymanu/{mid}")
	public ResponseEntity<?> getListByManu(@PathVariable("mid") Integer mid) {
		List<ProductDTO> result = pService.listByManu(mid);
		return ResponseEntity.ok(result);
	}
}
