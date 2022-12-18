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

	UpdateCarResponse update(UpdateCarRequest updateCarRequest,String id);

	void delete(String id);

	void changeCarState(String id,int state);

    void carAvialibleState(String carId);
	
}
