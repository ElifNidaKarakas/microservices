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
	@GetMapping
    public List<GetAllRentalResponse> getAll(){
        return rentalService.getAll();
    }

	@PostMapping
	public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest,
			@RequestParam String cardNumber, @RequestParam String fullName, @RequestParam String cardCvv, @RequestParam double price) {

		CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
		paymentRequest.setCardNumber(cardNumber);
		paymentRequest.setFullName(fullName);
		
		paymentRequest.setCardCvv(cardCvv);

		return rentalService.add(createRentalRequest, paymentRequest);
	}

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
	}

}
