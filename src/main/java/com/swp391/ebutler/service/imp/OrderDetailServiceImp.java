package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp391.ebutler.entities.OrderDetail;
import com.swp391.ebutler.model.dto.OrderDetailDTO;
import com.swp391.ebutler.model.mapper.OrderDetailMapper;
import com.swp391.ebutler.repositories.OrderDetailRepository;
import com.swp391.ebutler.service.OrderDetailService;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

	@Autowired
	OrderDetailRepository oderdtrepo;
	
	@Override
	public List<OrderDetailDTO> listOrderDetailByOrderId(int id) {
	List<OrderDetail> orderdetail = oderdtrepo.ListDetailByOrderId(id);
	List<OrderDetailDTO> dto = new ArrayList<>();
	orderdetail.forEach(v-> dto.add(OrderDetailMapper.toOrderDetailDTO(v)));
		return dto;
	}

	
}
