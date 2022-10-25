package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Services;
import com.swp391.ebutler.model.dto.ServicesDTO;

public class ServicesMapper {
	public static ServicesDTO toServicesDTO(Services s) {
		ServicesDTO sDTO = new ServicesDTO();
		sDTO.setServiceId(s.getServiceId());
		sDTO.setServiceName(s.getServiceName());
		sDTO.setDescription(s.getDescription());
		sDTO.setImage(s.getImage());
		sDTO.setStatus(s.getStatus());
		sDTO.setServicecategoryId(s.getSerCategory().getServicecategoryId());
		return sDTO;
	}
}
