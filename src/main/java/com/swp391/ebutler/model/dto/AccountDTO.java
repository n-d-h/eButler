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
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	private Integer accountId;
	
	@NotNull(message = "Login Mail is required")
	@Email
	private String loginMail;
	
	@Size(min=6, message = "Password must have at least 6 character")
	@NotNull(message = "Password is required")
	private String password;
	
	@NotNull
	private Boolean status;
	
	@NotNull
	private Integer providerId;
	
	@NotNull
	private Integer customerId;
}
