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

@Table(name = "tbl_product_category")
@Entity
@Data
@NoArgsConstructor
public class ProductCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_category_id", nullable = false, updatable = false)
	private Integer productcategoryId;
	
	@Column(name="product_category_name")
	private String productcategoryName;
	
	@Column(name="status")
	private Boolean status;
	
	@OneToMany( mappedBy = "proCategory", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Product> products;

	public ProductCategory(Integer productcategoryId, String productcategoryName, Boolean status) {
		super();
		this.productcategoryId = productcategoryId;
		this.productcategoryName = productcategoryName;
		this.status = status;
	} 
	
	
}
