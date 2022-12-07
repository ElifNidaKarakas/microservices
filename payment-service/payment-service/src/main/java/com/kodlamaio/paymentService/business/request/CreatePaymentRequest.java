package com.kodlamaio.paymentService.business.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePaymentRequest {

	 private String cardNo;
	
	 private String cardHolder;
	 
	 private String cvv;
	 
	 private LocalDate cardDate;
	 
	 private String rentalId;
	 
	 private double balance;//kart limiti
	 
	 private int statusPayment;
}
