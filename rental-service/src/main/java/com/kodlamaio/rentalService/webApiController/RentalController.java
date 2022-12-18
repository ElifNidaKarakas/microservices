package com.kodlamaio.rentalService.webApiController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	private RentalService rentalService;
<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java
	@GetMapping
    public List<GetAllRentalResponse> getAll(){
        return rentalService.getAll();
    }

	@PostMapping
	public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest,
			@RequestParam String cardNumber, @RequestParam String fullName, @RequestParam String cardCvv, @RequestParam double price) {
=======

	@PostMapping
	public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest,
			@RequestParam String cardNumber, @RequestParam String fullName, @RequestParam int cardExpirationYear,
			@RequestParam int cardExpirationMonth, @RequestParam String cardCvv, @RequestParam double price) {
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java

		CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
		paymentRequest.setCardNumber(cardNumber);
		paymentRequest.setFullName(fullName);
<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java
		
=======
		paymentRequest.getCardExpirationYear();
		paymentRequest.getCardExpirationMonth();
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java
		paymentRequest.setCardCvv(cardCvv);

		return rentalService.add(createRentalRequest, paymentRequest);
	}

<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java
	@PutMapping("/{id}")
	public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest,
		@PathVariable String id) {

		return rentalService.update(updateRentalRequest,id);
	}

//	@GetMapping
//	public GetRentalResponse getById(@PathVariable String id) {
//		return rentalService.getById(id);
//	}

	@DeleteMapping
	public void delete(String id) {
		rentalService.delete(id);
=======
	@PutMapping()
	public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest) {

		return rentalService.update(updateRentalRequest);
	}

	@GetMapping("/totalpricebyid/{id}")
	public double getTotalPrice(@PathVariable String id) {
		return rentalService.getTotalPrice(id);
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/webApiController/RentalController.java
	}

}
