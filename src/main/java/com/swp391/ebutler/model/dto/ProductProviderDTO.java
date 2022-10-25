package com.swp391.ebutler.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductProviderDTO {
	
	private Integer productproviderId;
	
	@Min(value = 0, message = "Price must greater than 0")
	private Float unitPrice;
	
	@Min(value = 0, message = "Quantity must greater than 0")
	private Integer quantity;
	
	@NotNull(message = "Description can not be empty")
	private String personalDescription;
	
	@Min(0)
	@Max(5)
	private Float rating;
	
	private Boolean status;
	
	private String image;
	
	@NotNull(message = "Please select product")
	private Integer product_id;
	
	@NotNull(message = "Please select provider")
	private Integer provider_id;
	
	private String productName;
	
}
