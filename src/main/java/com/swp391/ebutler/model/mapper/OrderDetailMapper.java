package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.OrderDetail;
import com.swp391.ebutler.model.dto.OrderDetailDTO;

public class OrderDetailMapper {

	public static OrderDetailDTO toOrderDetailDTO(OrderDetail ord) {
		OrderDetailDTO dto = new OrderDetailDTO();
		dto.setOrderDetailId(ord.getOrderDetailId());
		dto.setUnitPrice(ord.getUnitPrice());
		dto.setQuantity(ord.getQuantity());
		dto.setOrderId(ord.getOrder().getOrderId());
		dto.setProductproviderId(ord.getPProvider().getProductproviderId());
		dto.setProductName(ord.getPProvider().getProduct().getProductName());
		return dto;
	}
}
