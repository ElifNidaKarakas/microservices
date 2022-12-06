package com.kodlamaio.paymentService.business.responses;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePaymentResponses {
	 private String cardNo;
		
	 private String cardHolder;
	 
	 private String cvv;
	 
	 private LocalDate cardDate;
	 
	 private String rentalId;
	 
	 private double balance;//kart limiti
	 
	 private int statusPayment;
}
