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

@Table(name = "tbl_manufacturer")
@Entity
@Data
@NoArgsConstructor
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="manufacturer_id", nullable = false, updatable = false)
	private Integer manufacturerId;
	
	@Column(name="manufacturer_name")
	private String manufacturerName;
	
	@Column(name="status")
	private Boolean status;
	
	@OneToMany(mappedBy = "manu", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Product> products; 
}
