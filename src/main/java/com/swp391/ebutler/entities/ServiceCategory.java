package com.swp391.ebutler.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tbl_service_category")
@Entity
@Data
@NoArgsConstructor
public class ServiceCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_category_id", nullable = false, updatable = false)
	private Integer servicecategoryId;
	
	@Column(name = "service_category_name")
	private String  servicecategoryName;
	
	@Column(name = "status")
	private Boolean status;
	
	// Primary key [service category Id]
	@OneToMany(mappedBy = "serCategory", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Services> service;
	
}
