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
public class ServicesDTO {

	private Integer serviceId;
	
	@NotNull(message = "Specify the service")
	private String serviceName;
	
	@NotNull
	private String description;
	
	@NotNull
	private String image;
	
	@NotNull
	private Boolean status;
	
	@NotNull
	private Integer servicecategoryId;
}
