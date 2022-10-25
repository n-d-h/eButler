package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.ServiceCategory;
import com.swp391.ebutler.entities.Services;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

	// find services by name 
	List<Services> findByServiceNameContaining(String serviceName);
	
	// find services by name and status
	List<Services> findByStatusAndServiceNameContaining(Boolean status, String serviceName);

	// find services by status
	List<Services> findByStatus(Boolean status);
	
	// find services by category
	List<Services> findBySerCategory(ServiceCategory serCategory);

	// find services by category and status
	List<Services> findBySerCategoryAndStatus(ServiceCategory serCategory, Boolean status);
	
	// find services by provider name
	@Query("Select s "
			+ "from Services s inner join ServiceProvider sp "
			+ "on s.serviceId = sp.service.serviceId "
			+ "inner join Provider p "
			+ "on sp.provider.providerId = p.providerId "
			+ "where p.providerName like %?1% ")
	List<Services> findByProviderName(String providerName);
	
	// find services by provider name and status true
	@Query("Select s " 
			+ "from Services s inner join ServiceProvider sp " 
			+ "on s.serviceId = sp.service.serviceId "
			+ "inner join Provider p " 
			+ "on sp.provider.providerId = p.providerId " 
			+ "where p.providerName like %?1% "
			+ "and s.status = 1")
	List<Services> findByActiveProviderName(String providerName);
}
