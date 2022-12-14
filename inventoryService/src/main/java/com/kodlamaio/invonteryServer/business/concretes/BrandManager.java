package com.kodlamaio.invonteryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filterService.BrandUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invonteryServer.business.abstracts.BrandService;
import com.kodlamaio.invonteryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateBrandResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetBrandResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.invonteryServer.dataAccess.BrandRepository;
import com.kodlamaio.invonteryServer.entities.Brand;
import com.kodlamaio.invonteryServer.kafka.producer.FilterServiceProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private FilterServiceProducer filterServiceProducer;
	

	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands = this.brandRepository.findAll();

		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());

		return response;
	}
	
	
	@Override
	public GetBrandResponse getById(String id) {
		checkIfBrandExistsById(id);
		Brand brand=this.brandRepository.findById(id).get();
		GetBrandResponse brandResponse=this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
		
		return brandResponse;
	}
	
	@Override
	public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		this.brandRepository.save(brand);

		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(brand,
				CreateBrandResponse.class);
		return createBrandResponse;
	}

	@Override
	public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistsById(updateBrandRequest.getId());
		checkIfBrandExistsByName(updateBrandRequest.getName());
		Brand brand=this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
		UpdateBrandResponse brandResponse=this.modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);
		

		updateToFilterServiceBrandName(brandResponse.getId(), brandResponse.getName());
		
		return brandResponse;
	}

	@Override
	public void delete(String id) {
		checkIfBrandExistsByName(id);
		brandRepository.deleteById(id);
	}
	
	
	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}

	private void checkIfBrandExistsById(String id) {
		var result = this.brandRepository.findById(id);
		if (result == null) {
			throw new BusinessException("BRAND.NO.EXISTS");
		}
	}
	
	
	 private void updateToFilterServiceBrandName(String id, String name) {
	        BrandUpdatedEvent event = new BrandUpdatedEvent();
	        event.setId(id);
	        event.setName(name);
	        filterServiceProducer.sendMessage(event);
	    }
	
	
}