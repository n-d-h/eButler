package com.swp391.ebutler.model.dto;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Integer orderId;
	private Float totalPrice;
	private Date orderDate;
	private String paymentType;
	private String shippingStatus;
	private Integer customerId;
	private String customerName;
	private List<OrderDetailDTO> orderDetailList;
}
