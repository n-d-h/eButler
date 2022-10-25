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

@Table(name = "tbl_service")
@Entity
@Data
@NoArgsConstructor
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id", nullable = false, updatable = false)
	private Integer serviceId;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "status")
	private Boolean status;
	
	// Foreign key [service category id]
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "service_category_id")
	private ServiceCategory serCategory;
	
	// Primary key [service id]
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<ServiceProvider> sProvider;

	// Constructor with parameters
	public Services(Integer serviceId, String serviceName, String description, String image, Boolean status,
			ServiceCategory serCategory) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.description = description;
		this.image = image;
		this.status = status;
		this.serCategory = serCategory;
	}
	
	
}
