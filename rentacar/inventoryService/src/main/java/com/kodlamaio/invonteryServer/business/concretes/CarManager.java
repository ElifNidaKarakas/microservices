package com.kodlamaio.invonteryServer.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filterService.CarCreatedEvent;
import com.kodlamaio.common.events.filterService.CarDeletedEvent;
import com.kodlamaio.common.events.filterService.CarUpdateEvent;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invonteryServer.business.abstracts.CarService;
import com.kodlamaio.invonteryServer.business.request.create.CreateCarRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateCarResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetCarResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateCarResponse;
import com.kodlamaio.invonteryServer.dataAccess.CarRepository;
import com.kodlamaio.invonteryServer.entities.Car;
import com.kodlamaio.invonteryServer.kafka.producer.FilterServiceProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private FilterServiceProducer filterServiceProducer;

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
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());
		carRepository.save(car);

//		Car savedCar = carRepository.findById(car.getId()).orElseThrow();
//		CarCreatedEvent event = new CarCreatedEvent();
//		event.setBrandId(savedCar.getModel().getBrand().getId());
//		event.setBrandName(savedCar.getModel().getBrand().getName());
//		event.setCarId(savedCar.getId());
//		event.setDailyPrice(savedCar.getDailyPrice());
//		event.setModelId(savedCar.getModel().getName());
//		event.setModelName(savedCar.getModel().getName());
//		event.setModelYear(savedCar.getModelYear());
//		event.setPlate(savedCar.getPlate());
//		event.setState(savedCar.getState());
//		filterServiceProducer.sendMessage(event);

		CreateCarResponse createCarResponse = this.modelMapperService.forResponse().map(car, CreateCarResponse.class);
		addToFilterService(createCarResponse.getId());
		//filtre servise eklememizi sağlar.
		return createCarResponse;
		
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest, String id) {
		checkIfCarExistsById(updateCarRequest.getId());
		//checkIfCarExistsByPlate(updateCarRequest.getPlate());
		//id ve plaka kontrolü yaparak update yapar.
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setId(id);
		car.setState(car.getState());
		
		carRepository.save(car);
		
		UpdateCarResponse carResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		
		updateToFilterService(id, updateCarRequest);

		return carResponse;
	}

	@Override
	public void delete(String id) {
		checkIfCarExistsById(id);
		  CarDeletedEvent event = new CarDeletedEvent();
	        event.setCarId(id);
	        filterServiceProducer.sendMessage(event);
		carRepository.deleteById(id);
	}

	@Override
	public void changeCarState(String id, int state) {
		carRepository.setStateByCarId(id, state);
	}

	@Override
	public void carAvialibleState(String carId) {
		checkIfCarAvialible(carId);
	}

	private Car checkIfCarExistsById(String id) {
		Car car = carRepository.findById(id).orElse(null);
		if (car == null) {
			throw new BusinessException("CAR.NOT_EXISTS");
		}
		return car;
	}

	private void checkIfCarExistsByPlate(String plate) {
		if (carRepository.existsByPlateIgnoreCase(plate)) {
			throw new BusinessException("CAR.ALREADY_EXISTS");
		}
	}

	private void checkIfCarAvialible(String carId) {
		Car car = carRepository.findById(carId).orElse(null);
		if (car == null || car.getState() != 1)
			throw new BusinessException("Car no exists or car no avialible");
	}

	private void addToFilterService(String id) {
		Car car=carRepository.findById(id).get();
		CarCreatedEvent event=modelMapperService.forResponse().map(car, CarCreatedEvent.class);
		filterServiceProducer.sendMessage(event);
		
	}
	
	
	private void updateToFilterService(String id, UpdateCarRequest updateCarRequest) {
		Car car = carRepository.findById(id).orElseThrow();
		car.getModel().setId(updateCarRequest.getModelId());
		car.getModel().getBrand().setId(car.getModel().getBrand().getId());
		car.setState(updateCarRequest.getState());
		car.setPlate(updateCarRequest.getPlate());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		CarUpdateEvent event = modelMapperService.forResponse().map(car, CarUpdateEvent.class);
		filterServiceProducer.sendMessage(event);
	}
	
	
	

}
