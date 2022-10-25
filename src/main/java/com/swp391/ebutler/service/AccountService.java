package com.swp391.ebutler.service;

import java.util.List;

import com.swp391.ebutler.entities.Account;
import com.swp391.ebutler.model.dto.LoginAccDTO;
import com.swp391.ebutler.model.dto.RegisterAccountDTO;

public interface AccountService {
	public List<Account> listAll();
	
	public RegisterAccountDTO registAcc( RegisterAccountDTO registacc);
	
	public RegisterAccountDTO login( LoginAccDTO acc);
}
