package com.kodlamaio.invonteryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.invonteryServer.business.request.create.CreateModelRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateModelResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetModelResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateModelResponse;

public interface ModelService {
	List<GetAllModelsResponse> getAll();

	GetModelResponse  getById(String id);

	CreateModelResponse add(CreateModelRequest createModelRequest);

	UpdateModelResponse update(UpdateModelRequest updateModelRequest);

	void delete(String id);
}
