package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;


public interface RentalService {
	List<GetAllRentalResponse> getAll();
	GetRentalResponse getById(String id);
	CreateRentalResponse add(CreateRentalRequest createRentalRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
	void delete(String id);
}