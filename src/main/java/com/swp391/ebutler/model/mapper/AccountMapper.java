package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Account;
import com.swp391.ebutler.model.dto.AccountDTO;

public class AccountMapper {

	public static AccountDTO toAccountDTO(Account acc) {
		AccountDTO dto = new AccountDTO();
		dto.setAccountId(acc.getAccountId());
		dto.setLoginMail(acc.getLoginMail());
		dto.setPassword(acc.getPassword());
		dto.setStatus(acc.getStatus());
		dto.setProviderId(acc.getProvider().getProviderId());
		dto.setCustomerId(acc.getCustomer().getCustomerId());
		return dto;
	}
}
