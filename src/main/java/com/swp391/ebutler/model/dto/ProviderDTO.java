package com.swp391.ebutler.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDTO {
	private Integer providerId;
	
	@NotNull(message = "Email is required")
	@Email
	private String email;
	
	@NotNull(message = "Name is required")
	private String providerName;
	
	@NotNull(message = "Phone number is required")
	private String phoneNumber;
	
	@NotNull@NotNull(message = "Address is required")
	private String address;
	
	@NotNull
	private Integer accountId;
}
