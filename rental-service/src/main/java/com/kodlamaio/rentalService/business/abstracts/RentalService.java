package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalService.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;


public interface RentalService {
	List<GetAllRentalResponse> getAll();
	GetRentalResponse getById(String id);
	CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest);
<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/business/abstracts/RentalService.java
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest,String id);
=======
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/business/abstracts/RentalService.java
	void delete(String id);
	
}
