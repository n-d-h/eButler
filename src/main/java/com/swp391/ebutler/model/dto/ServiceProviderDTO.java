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
public class ServiceProviderDTO {

	private Integer serviceproviderId;
	
	@NotNull
	private Integer rating;
	
	@NotNull
	private Integer providerId;
	
	@NotNull
	private Integer serviceId;
	
	@NotNull(message = "Description can not be empty")
	private String personalDescription;

	@NotNull(message = "Specify price range for the service")
	private Float minPrice;
	
	@NotNull(message = "Specify price range for the service")
	private Float maxPrice;
	
	private String serviceName;
	
	private String image;
	
	@NotNull
	private Boolean status;
}
