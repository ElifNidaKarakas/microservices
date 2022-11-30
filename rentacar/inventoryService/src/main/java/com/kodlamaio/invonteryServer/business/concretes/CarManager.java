package com.kodlamaio.invonteryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invonteryServer.business.abstracts.CarService;
import com.kodlamaio.invonteryServer.business.abstracts.ModelService;
import com.kodlamaio.invonteryServer.business.request.create.CreateCarRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateCarResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetCarResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateCarResponse;
import com.kodlamaio.invonteryServer.dataAccess.CarRepository;
import com.kodlamaio.invonteryServer.entities.Car;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private ModelService modelService;

	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> car = this.carRepository.findAll();
		List<GetAllCarsResponse> response = car.stream()
				.map(responses -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public GetCarResponse getById(String id) {
		checkIfCarExistsById(id);
		Car car = carRepository.findById(id).get();
		GetCarResponse carResponse = this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return carResponse;
	}

	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		checkIfModelExistsByModelId(createCarRequest.getModelId());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRepository.save(car);

		CreateCarResponse createCarResponse = this.modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return createCarResponse;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistsById(updateCarRequest.getId());
		checkIfCarExistsByPlate(updateCarRequest.getPlate());
		checkIfModelExistsByModelId(updateCarRequest.getModelId());;
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		carRepository.save(car);

		UpdateCarResponse carResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return carResponse;
	}

	@Override
	public void delete(String id) {
		carRepository.deleteById(id);
	}

	private void checkIfCarExistsById(String id) {
		var result = carRepository.findById(id);
		if (result == null ) {
			throw new BusinessException("CAR NO EXISTS");
		}
	}
	
	private void checkIfCarExistsByPlate(String plate) {
		var result = carRepository.findByPlate(plate);
		if (result != null) {
			throw new BusinessException("CAR EXISTS");
		}
	}
	
	private void checkIfModelExistsByModelId(String modelId) {
		var result = modelService.getById(modelId);
		if (result == null) {
			throw new BusinessException("MODEL NO EXİSTS");
		}
	}

}