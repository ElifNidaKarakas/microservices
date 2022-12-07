package com.kodlamaio.invonteryServer.webApiControllor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invonteryServer.business.abstracts.ModelService;
import com.kodlamaio.invonteryServer.business.request.create.CreateModelRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateModelRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateModelResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetModelResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateModelResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/models")
@RestController
public class ModelController {
	private ModelService modelService;

	@GetMapping
	public List<GetAllModelsResponse> getAll() {
		return modelService.getAll();
	}

	@GetMapping("/{id}")
	public GetModelResponse getById(@PathVariable String id) {
		return modelService.getById(id);
	}

	@PostMapping
	public CreateModelResponse add(@RequestBody CreateModelRequest createModelRequest) {
		return modelService.add(createModelRequest);
	}

	@PutMapping
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		return modelService.update(updateModelRequest);
	}

	@DeleteMapping
	public void delete(String id) {
      modelService.delete(id);
	}
}
