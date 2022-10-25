package com.swp391.ebutler.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tbl_product_provider")
@Entity
@Data
@NoArgsConstructor
public class ProductProvider {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_provider_id", nullable = false, updatable = false)
	private Integer productproviderId;
	
	@Column(name="unit_price")
	private Float unitPrice;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="personal_description")
	private String personalDescription;
	
	@Column(name="rating")
	private Float rating;
	
	@Column(name="status")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	@JsonBackReference
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;
	
	@OneToMany(mappedBy = "pProvider", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<OrderDetail> orderDetails;

	public ProductProvider(Integer productproviderId, Float unitPrice, Integer quantity, String personalDescription,
			Float rating, Boolean status, Provider provider, Product product) {
		super();
		this.productproviderId = productproviderId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.personalDescription = personalDescription;
		this.rating = rating;
		this.status = status;
		this.provider = provider;
		this.product = product;
	}

	

}
