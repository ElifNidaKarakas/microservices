package com.kodlamaio.invonteryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invonteryServer.business.abstracts.BrandService;
import com.kodlamaio.invonteryServer.business.abstracts.ModelService;
import com.kodlamaio.invonteryServer.business.request.create.CreateModelRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateModelResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetModelResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateModelResponse;
import com.kodlamaio.invonteryServer.dataAccess.ModelRepository;
import com.kodlamaio.invonteryServer.entities.Model;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	private BrandService brandService;

	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> model = modelRepository.findAll();
		List<GetAllModelsResponse> modelResponses = model.stream()
				.map(models -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
				.collect(Collectors.toList());
		return modelResponses;
	}

	@Override
	public GetModelResponse getById(String id) {
		checkIfModelExistsById(id);
        Model model=this.modelRepository.findById(id).get();
        GetModelResponse modelResponse=this.modelMapperService.forResponse().map(model, GetModelResponse.class);
		return modelResponse;
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {
		checkIfBrandExistsByBrandId(createModelRequest.getBrandId());
		checkIfModelExistsByName(createModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		modelRepository.save(model);

		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(model,
				CreateModelResponse.class);
		return createModelResponse;
	}

	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		checkIfBrandExistsByBrandId(updateModelRequest.getBrandId());
		checkIfModelExistsByName(updateModelRequest.getName());
		checkIfModelExistsById(updateModelRequest.getId());
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		modelRepository.save(model);

		UpdateModelResponse modelResponse = this.modelMapperService.forResponse().map(model, UpdateModelResponse.class);
		return modelResponse;
	}

	@Override
	public void delete(String id) {
		checkIfModelExistsById(id);
		modelRepository.deleteById(id);
	}
	
	private void checkIfModelExistsByName(String name) {
		Model currentModel = this.modelRepository.findByName(name);
		if (currentModel != null) {
			throw new BusinessException("MODEL.EXISTS");
		}
	}
	
	private void checkIfModelExistsById(String id) {
		var result = this.modelRepository.findById(id).orElse(null);
		if (result == null) {
			throw new BusinessException("MODEL.NO.EXISTS");
		}
	}
	
	private void checkIfBrandExistsByBrandId(String brandId) {
		var result = this.brandService.getById(brandId);
		if (result == null) {
			throw new BusinessException("BRAND.NO.EXISTS");
		}
	}

}
