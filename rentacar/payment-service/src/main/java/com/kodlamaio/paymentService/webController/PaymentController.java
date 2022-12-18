package com.kodlamaio.paymentService.webController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.request.UpdatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;
import com.kodlamaio.paymentService.business.responses.GetAllPaymentsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
	private PaymentService paymentService;
	
	@GetMapping
    public List<GetAllPaymentsResponse> getAll() {
		return paymentService.getAll();
	}
	
	@PostMapping
	public CreatePaymentResponses add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
		return paymentService.add(createPaymentRequest);
	}
	
	 @PostMapping("/check")
	    public void checkIfPaymentSuccessful(
	            @RequestParam String cardNumber,
	            @RequestParam String fullName,
	            @RequestParam String cardCvv,
	            @RequestParam double price) {
	        PaymentRequest request =
	                new PaymentRequest(cardNumber,
	                                   fullName,
	                                   
	                                   cardCvv,
	                                   price);
	        paymentService.checkIfPaymentSuccessful(request);
	    }
	 
	 @PutMapping
	  public void update(String id, @RequestBody UpdatePaymentRequest request) {
		paymentService.update(id, request);
	 }
	 

	    @PutMapping("/{id}")
	    public void setBalance(@PathVariable String id, @RequestBody UpdatePaymentRequest updatePaymentRequest){
	        paymentService.update(id,updatePaymentRequest);
	    }
}
