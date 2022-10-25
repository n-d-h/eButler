package com.swp391.ebutler.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.swp391.ebutler.entities.Order;
import com.swp391.ebutler.entities.OrderDetail;
import com.swp391.ebutler.model.dto.OrderDTO;
import com.swp391.ebutler.model.dto.OrderDetailDTO;

public class OrderMapper {

	public static OrderDTO toOrderDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setOrderId(order.getOrderId());
		dto.setTotalPrice(order.getTotalPrice());
		dto.setOrderDate(order.getOrderDate());
		dto.setPaymentType(order.getPaymentType());
		dto.setShippingStatus(order.getShippingStatus());
		dto.setCustomerId(order.getCustomer().getCustomerId());
		dto.setCustomerName(order.getCustomer().getFullName());
		
		List<OrderDetailDTO> detaildto = new ArrayList<>();
		List<OrderDetail> detail = new ArrayList<>();
		detail.addAll(order.getOrderDetailList());
		detail.forEach(v -> detaildto.add(OrderDetailMapper.toOrderDetailDTO(v)));
		
		dto.setOrderDetailList(detaildto);
		return dto;
	}
}
