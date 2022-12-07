package com.kodlamaio.rentalService.webApiController;

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
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	private RentalService rentalService;

	@PostMapping
	public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest,
			@RequestParam String cardNumber, @RequestParam String fullName, @RequestParam int cardExpirationYear,
			@RequestParam int cardExpirationMonth, @RequestParam String cardCvv, @RequestParam double price) {

		CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
		paymentRequest.setCardNumber(cardNumber);
		paymentRequest.setFullName(fullName);
		paymentRequest.getCardExpirationYear();
		paymentRequest.getCardExpirationMonth();
		paymentRequest.setCardCvv(cardCvv);

		return rentalService.add(createRentalRequest, paymentRequest);
	}

	@PutMapping()
	public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest) {

		return rentalService.update(updateRentalRequest);
	}

	@GetMapping("/totalpricebyid/{id}")
	public double getTotalPrice(@PathVariable String id) {
		return rentalService.getTotalPrice(id);
	}

}
