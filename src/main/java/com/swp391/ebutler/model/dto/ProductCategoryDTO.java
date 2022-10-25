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
public class ProductCategoryDTO {
	
	private Integer productcategoryId;
	
	@Size(min=2,max=50, message = "Category name must have more than 5 and less than 50 characters")
	@NotNull(message = "Category name can not be empty")
	private String productcategoryName;
	
	private Boolean status;
	
}
