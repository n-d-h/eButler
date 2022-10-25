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
import lombok.NoArgsConstructor;

@Table(name = "tbl_service_provider")
@Entity
@Data
@NoArgsConstructor
public class ServiceProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_provider_id", nullable = false, updatable = false)
	private Integer serviceproviderId;
	
	@Column(name = "rating")
	private Integer rating;
	
	// Foreign key 1 [provider id]
	@ManyToOne
	@JoinColumn(name = "provider_id")
	@JsonBackReference
	private Provider provider;
	
	// Foreign key 2 [service id]
	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonBackReference
	private Services service;
	
	@Column(name = "personal_description")
	private String personalDescription;
	
	@Column(name = "min_price")
	private Float minPrice;
	
	@Column(name = "max_price")
	private Float maxPrice;
	
	@Column(name = "status")
	private Boolean status;

	// Constructor with parameters
	public ServiceProvider(Integer serviceproviderId, Integer rating, Provider provider, Services service,
			String personalDescription, Float minPrice, Float maxPrice, Boolean status) {
		super();
		this.serviceproviderId = serviceproviderId;
		this.rating = rating;
		this.provider = provider;
		this.service = service;
		this.personalDescription = personalDescription;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.status = status;
	}



	
}
