package com.swp391.ebutler.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountDTO {

	@NotNull(message = "Login Mail is required")
	@Email
	private String loginMail;
	
	@NotNull
	@Size(min = 6, message = "Password must has atleast 6 characters")
	private String password;
	
	@NotNull(message = "Name is required")
	private String fullName;
	
	@NotNull(message = "Phone Number is required")
	@Size(min = 10, max = 12, message = "Phone Number must be between 10 and 12 characters")
	private String phoneNumber;
	
	@NotNull(message = "Address is required")
	private String address;
	
	private Boolean role;
	
	private Integer Id;
}
