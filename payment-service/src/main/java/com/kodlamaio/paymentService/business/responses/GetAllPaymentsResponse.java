package com.kodlamaio.paymentService.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllPaymentsResponse {
	 private String id;
	    private String cardNumber;
	    private String fullName;
	
	    private String cardCvv;
	    private double balance;
}
