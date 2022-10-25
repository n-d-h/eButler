package com.swp391.ebutler.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategoryDTO {

	private Integer servicecategoryId;
	
	@NotNull(message = "Specify the service category")
	private String servicecategoryName;
	
	@NotNull
	private Boolean status;

}
