package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.Order;
import com.swp391.ebutler.model.dto.OrderDTO;

public interface OrderService {

	public Order save(OrderDTO order);
	
	public List<OrderDTO> getOrderByProvider(int id);
	
	public List<OrderDTO> getOrderByCus(int id);

	public List<OrderDTO> getOrderByProduct(int id);
	
	public OrderDTO getOrderById(int id);
	
	public OrderDTO changeOrderStatus(int id, int status);
	
	public List<OrderDTO> getOrderByStatus(int status);
}
