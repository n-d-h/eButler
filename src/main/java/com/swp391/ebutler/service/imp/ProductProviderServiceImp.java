package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp391.ebutler.entities.Product;
import com.swp391.ebutler.entities.ProductProvider;
import com.swp391.ebutler.entities.Provider;
import com.swp391.ebutler.model.dto.ProductProviderDTO;
import com.swp391.ebutler.model.dto.SubProductProviderDTO;
import com.swp391.ebutler.model.mapper.ProductProviderMapper;
import com.swp391.ebutler.repositories.ProductProviderRepository;
import com.swp391.ebutler.repositories.ProductRepository;
import com.swp391.ebutler.repositories.ProviderRepository;
import com.swp391.ebutler.service.ProductProviderService;

@Service
@Transactional
public class ProductProviderServiceImp implements ProductProviderService {
	@Autowired
	ProductProviderRepository pProviderRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	ProviderRepository providerRepo;

	@Override
	public List<ProductProviderDTO> listAll() {
		List<ProductProvider> result = pProviderRepo.findAll();
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public SubProductProviderDTO save(ProductProviderDTO pProviderDTO) {
		if (getIdByDTO(pProviderDTO) == -1) {
			ProductProvider pProvider = toProductProvider(pProviderDTO);
			ProductProviderMapper.toProductProviderDTO(pProviderRepo.save(pProvider));
			return new SubProductProviderDTO(-1, true);
		}
		return toSubProductProvider(getById(getIdByDTO(pProviderDTO)));
	}
	
	public SubProductProviderDTO toSubProductProvider(ProductProvider pProvider) {
		SubProductProviderDTO sub = new SubProductProviderDTO();
		sub.setProductproviderId(pProvider.getProductproviderId());
		sub.setStatus(pProvider.getStatus());
		return sub;
	}

	@Override
	public ProductProviderDTO update(ProductProviderDTO pProviderDTO) {
		ProductProvider pProvider = toProductProvider(pProviderDTO);
		return ProductProviderMapper.toProductProviderDTO(pProviderRepo.save(pProvider));
	}

	@Override
	public ProductProviderDTO delete(Integer id) {
		ProductProvider pProvider = getById(id);
		if (pProvider != null) {
			pProvider.setStatus(false);
			return ProductProviderMapper.toProductProviderDTO(pProviderRepo.save(pProvider));
		}
		return null;
	}

	@Override
	public ProductProviderDTO getByIdDTO(Integer id) {
		ProductProvider result = pProviderRepo.findById(id).get();
		return ProductProviderMapper.toProductProviderDTO(result);
	}

	@Override
	public ProductProvider getById(Integer id) {
		return pProviderRepo.findById(id).get();
	}

	public ProductProvider toProductProvider(ProductProviderDTO pProviderDTO) {
		ProductProvider pProvider = new ProductProvider();
		pProvider.setProductproviderId(pProviderDTO.getProductproviderId());
		pProvider.setRating(pProviderDTO.getRating());
		pProvider.setUnitPrice(pProviderDTO.getUnitPrice());
		pProvider.setPersonalDescription(pProviderDTO.getPersonalDescription());
		pProvider.setQuantity(pProviderDTO.getQuantity());
		pProvider.setStatus(pProviderDTO.getStatus());
		pProvider.setProduct(getProductById(pProviderDTO.getProduct_id()));
		pProvider.setProvider(getProviderById(pProviderDTO.getProvider_id()));
		return pProvider;
	}

	public Product getProductById(int id) {
		return productRepo.findById(id).get();
	}

	public Provider getProviderById(int id) {
		return providerRepo.findById(id).get();
	}

	@Override
	public Integer countByProductId(Integer id) {
		return pProviderRepo.countByProductId(id);
	}

	@Override
	public List<ProductProviderDTO> listAllFoCus() {
		List<ProductProvider> result = pProviderRepo.findByStatus(true);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByProductId(Integer id) {
		Product product = getProductById(id);
		List<ProductProvider> result = pProviderRepo.findByProduct(product);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByProviderId(Integer id) {
		Provider provider = getProviderById(id);
		List<ProductProvider> result = pProviderRepo.findByProvider(provider);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> sortInt(Integer sort, Integer pid) {
		List<ProductProvider> result = null;
		Product product = getProductById(pid);
		if (sort == 0) {
			result = pProviderRepo.findByStatusAndProduct(true, product, Sort.by(Direction.ASC, "unitPrice"));
		} else if (sort == 1) {
			result = pProviderRepo.findByStatusAndProduct(true, product, Sort.by(Direction.DESC, "unitPrice"));
		} else if (sort == 2) {
			result = pProviderRepo.findByStatusAndProduct(true, product, Sort.by(Direction.ASC, "rating"));
		} else if (sort == 3) {
			result = pProviderRepo.findByStatusAndProduct(true, product, Sort.by(Direction.ASC, "rating"));
		} else {
			result = pProviderRepo.findByStatus(true);
		}
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByProductIdFoCus(Integer id) {
		Product product = getProductById(id);
		List<ProductProvider> result = pProviderRepo.findByProductAndStatus(product, true);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByProviderIdFoCus(Integer id) {
		Provider provider = getProviderById(id);
		List<ProductProvider> result = pProviderRepo.findByProviderAndStatus(provider, true);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByManuId(Integer id) {
		List<ProductProvider> result = pProviderRepo.listByManuId(id);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public List<ProductProviderDTO> listByCateId(Integer id) {
		List<ProductProvider> result = pProviderRepo.listByCateId(id);
		List<ProductProviderDTO> listDTO = new ArrayList<>();
		result.forEach(v -> listDTO.add(ProductProviderMapper.toProductProviderDTO(v)));
		return listDTO;
	}

	@Override
	public Integer getIdByDTO(ProductProviderDTO pProviderDTO) {
		Integer id = -1;
		Product product = getProductById(pProviderDTO.getProduct_id());
		Provider provider = getProviderById(pProviderDTO.getProvider_id());
		ProductProvider pProvider = pProviderRepo.findByProductAndProvider(product, provider);
		if ( pProvider != null) {
			id = pProvider.getProductproviderId();
		}
		return id;
	}

}
