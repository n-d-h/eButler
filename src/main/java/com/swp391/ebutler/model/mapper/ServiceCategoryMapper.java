package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.ServiceCategory;
import com.swp391.ebutler.model.dto.ServiceCategoryDTO;

public class ServiceCategoryMapper {
	public static ServiceCategoryDTO toServiceCategoryDTO(ServiceCategory sc) {
		ServiceCategoryDTO scDTO = new ServiceCategoryDTO();
		scDTO.setServicecategoryId(sc.getServicecategoryId());
		scDTO.setServicecategoryName(sc.getServicecategoryName());
		scDTO.setStatus(sc.getStatus());
		return scDTO;
	}
}
