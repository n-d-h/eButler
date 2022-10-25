package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.ServiceProvider;
import com.swp391.ebutler.model.dto.ServiceProviderDTO;

public class ServiceProviderMapper {
	public static ServiceProviderDTO toServiceProviderDTO(ServiceProvider sp) {
		ServiceProviderDTO spDTO = new ServiceProviderDTO();
		spDTO.setServiceproviderId(sp.getServiceproviderId());
		spDTO.setRating(sp.getRating());
		spDTO.setServiceId(sp.getService().getServiceId());
		spDTO.setProviderId(sp.getProvider().getProviderId());
		spDTO.setServiceName(sp.getService().getServiceName());
		spDTO.setPersonalDescription(sp.getPersonalDescription());
		spDTO.setMinPrice(sp.getMinPrice());
		spDTO.setMaxPrice(sp.getMaxPrice());
		spDTO.setStatus(sp.getStatus());
		spDTO.setImage(sp.getService().getImage());
		return spDTO;
	}
}
