package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Provider;
import com.swp391.ebutler.model.dto.ProviderDTO;

public class ProviderMapper {

	public static ProviderDTO toProviderDTO(Provider pro) {
		ProviderDTO dto = new ProviderDTO();
		dto.setProviderId(pro.getProviderId());
		dto.setEmail(pro.getEmail());
		dto.setProviderName(pro.getProviderName());
		dto.setPhoneNumber(pro.getPhoneNumber());
		dto.setAddress(pro.getAddress());
		dto.setAccountId(pro.getAccount().getAccountId());
		return dto;
	}
}
