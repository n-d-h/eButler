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
public class CustomerDTO {
	
	private Integer customerId;
	
	@NotNull(message = "Login Mail is required")
	@Email
	private String email;
	
	@NotNull
	private String fullName;
	
	@NotNull(message = "Phone Number is required for personal orders")
	private String phoneNumber;
	
	@NotNull
	private String address;

	@NotNull
	private Integer accountId;
}
