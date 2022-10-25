package com.swp391.ebutler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

	private Integer orderDetailId;
	private Float unitPrice;
	private Integer quantity;
	private Integer orderId;
	private Integer productproviderId;
	private String productName;
}
