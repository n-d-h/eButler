package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value="Select distinct o.order_id from tbl_order o \r\n"
			+ "join tbl_order_detail l on o.order_id=l.order_id \r\n"
			+ "join tbl_product_provider v on v.product_provider_id = l.product_provider_id\r\n"
			+ "join tbl_provider b on v.provider_id=b.provider_id\r\n"
			+ "where b.provider_id = :id", nativeQuery = true)
	List<Integer> listOrderByProvider(int id);
	
	
	@Query(value="SELECT o FROM Order o WHERE o.customer.customerId = :id")
	List<Order> listOrderByCus(int id);
	
	@Query(value="Select o.order_id from tbl_order o \r\n"
			+ "join tbl_order_detail l on o.order_id=l.order_id \r\n"
			+ "join tbl_product_provider v on v.product_provider_id = l.product_provider_id\r\n"
			+ "join tbl_product b on v.product_id=b.product_id\r\n"
			+ "where b.provider_id = :id", nativeQuery = true)
	List<Integer> listOrderByProduct(int id);
	
	List<Order> findByShippingStatusLike(String status);
}
