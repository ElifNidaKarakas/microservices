package com.kodlamaio.paymentService.business.responses;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePaymentResponses {
	 private String id;
	    private String cardNumber;
	    private String fullName;
	
	    private String cardCvv;
	    private double balance;
}
