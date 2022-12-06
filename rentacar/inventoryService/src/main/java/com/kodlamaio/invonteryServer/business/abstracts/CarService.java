package com.kodlamaio.invonteryServer.business.abstracts;

import java.util.List;

import com.kodlamaio.invonteryServer.business.request.create.CreateCarRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateCarResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetCarResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateCarResponse;

public interface CarService {
	List<GetAllCarsResponse> getAll();

	GetCarResponse getById(String id);

	CreateCarResponse add(CreateCarRequest createCarRequest);

	UpdateCarResponse update(UpdateCarRequest updateCarRequest);

	void delete(String id);

	void changeState(String id);
	void checkIfCarAvailable(String id);
	UpdateCarResponse updateCarState(String carId,int state);
	
}
