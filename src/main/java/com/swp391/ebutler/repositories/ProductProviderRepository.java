package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.Product;
import com.swp391.ebutler.entities.ProductProvider;
import com.swp391.ebutler.entities.Provider;

public interface ProductProviderRepository extends JpaRepository<ProductProvider, Integer>{
	@Query("Select Count(*) from ProductProvider p where p.product.productId = ?1")
	Integer countByProductId(Integer id);
	
	List<ProductProvider> findByStatus(Boolean status);
	
	List<ProductProvider> findByStatusAndProduct(Boolean status,Product product, Sort sort);
	
	List<ProductProvider> findByProduct(Product product);
	
	List<ProductProvider> findByProvider(Provider provider);
	
	List<ProductProvider> findByProductAndStatus(Product product, Boolean status);
	
	List<ProductProvider> findByProviderAndStatus(Provider provider, Boolean status);
	
	@Query("SELECT pp FROM "
			+ "ProductProvider pp INNER JOIN Product p "
			+ "ON pp.product.productId = p.productId "
			+ "WHERE p.manu.manufacturerId = ?1 ")
	List<ProductProvider> listByManuId(Integer id);
	
	@Query("SELECT pp FROM "
			+ "ProductProvider pp INNER JOIN Product p "
			+ "ON pp.product.productId = p.productId "
			+ "WHERE p.proCategory.productcategoryId = ?1 ")
	List<ProductProvider> listByCateId(Integer id);

	ProductProvider findByProductAndProvider(Product product, Provider provider);
}
