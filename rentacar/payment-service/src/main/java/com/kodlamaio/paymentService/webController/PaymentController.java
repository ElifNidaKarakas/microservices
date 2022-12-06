package com.kodlamaio.paymentService.webController;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
	private PaymentService paymentService;
	
	@PostMapping
	public CreatePaymentResponses add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}
	
	 @PostMapping("/check")
	    public void checkIfPaymentSuccessful(
	            @RequestParam String cardNumber,
	            @RequestParam String fullName,
	            @RequestParam int cardExpirationYear,
	            @RequestParam int cardExpirationMonth,
	            @RequestParam String cardCvv,
	            @RequestParam double price) {
	        PaymentRequest request =
	                new PaymentRequest(cardNumber,
	                                   fullName,
	                                   cardExpirationYear,
	                                   cardExpirationMonth,
	                                   cardCvv,
	                                   price);
	        paymentService.checkIfPaymentSuccessful(request);
	    }
}
