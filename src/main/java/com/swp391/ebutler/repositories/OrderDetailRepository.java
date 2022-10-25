package com.swp391.ebutler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swp391.ebutler.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	@Query(value="SELECT d FROM OrderDetail d WHERE d.order.orderId = :id")
	List<OrderDetail> ListDetailByOrderId(Integer id);
}
