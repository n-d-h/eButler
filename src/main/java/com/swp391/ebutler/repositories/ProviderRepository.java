package com.swp391.ebutler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer>{

	Provider findByProviderId(Integer providerId);
	
	@Query("SELECT p FROM Provider p Where p.account.accountId= :id")
	Provider findByAccountId(Integer id);
}
