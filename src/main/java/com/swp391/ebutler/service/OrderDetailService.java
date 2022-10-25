package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.model.dto.OrderDetailDTO;

public interface OrderDetailService {

	public List<OrderDetailDTO> listOrderDetailByOrderId(int id);
}
