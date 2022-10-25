package com.swp391.ebutler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Table(name = "tbl_order_detail")
@Entity
@Data
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_detail_id", nullable = false, updatable = false)
	private Integer orderDetailId;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "orderId")
	private Order order;
	
	@Column(name="unit_price")
	private Float unitPrice;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "product_provider_id")
	private ProductProvider pProvider;
}
