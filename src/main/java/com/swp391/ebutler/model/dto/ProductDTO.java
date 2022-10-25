package com.swp391.ebutler.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Integer productId;
	
	@Size(min=2,max=50, message = "Product name must have at least 5")
	@NotNull(message = "Product name can not be empty")
	private String productName;
	
	@NotNull(message = "Description can not be empty")
	private String description;
	
	@NotNull(message = "Image can not be empty")
	private String image;
	
	private Boolean status;
	
	@NotNull(message = "Please select category")
	private Integer productcategoryId;
	
	@NotNull(message = "Please select manufacturer")
	private Integer manufacturerId;
	
}
