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
public class ManufacturerDTO {
	private Integer manufacturerId;
	
	@Size(min=2, message = "Manufacturer name must have more than 2 and less than 50 characters")
	@NotNull(message = "Manufacturer name can not be empty")
	private String manufacturerName;
	
	private Boolean status;
}
