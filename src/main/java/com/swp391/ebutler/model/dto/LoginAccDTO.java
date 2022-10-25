package com.swp391.ebutler.model.dto;

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
public class LoginAccDTO {

	
	@NotNull(message = "Login Mail is required")
	private String loginMail;
	
	@NotNull(message = "Password is required")
	@Size(min = 6, message = "Password must has atleast 6 characters")
	private String password;
}
