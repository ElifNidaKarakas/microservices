package com.kodlamaio.rentalService.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {
	 private String carId;
	    private int rentedForDays;
	    private double dailyPrice;
	    private double totalPrice;
	
}
