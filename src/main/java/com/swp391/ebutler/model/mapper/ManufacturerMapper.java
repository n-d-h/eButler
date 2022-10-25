package com.swp391.ebutler.model.mapper;

import com.swp391.ebutler.entities.Manufacturer;
import com.swp391.ebutler.model.dto.ManufacturerDTO;

public class ManufacturerMapper {
	public static ManufacturerDTO toManufacturerDTO(Manufacturer manu) {
		ManufacturerDTO manuDTO = new ManufacturerDTO();
		manuDTO.setManufacturerId(manu.getManufacturerId());
		manuDTO.setManufacturerName(manu.getManufacturerName());
		manuDTO.setStatus(manu.getStatus());
		return manuDTO;
	}
}
