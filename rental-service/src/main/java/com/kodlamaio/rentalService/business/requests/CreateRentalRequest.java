package com.kodlamaio.rentalService.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalRequest {
	
	private String carId;

	private int rentedForDays;
	
	private double dailyPrice;
	

}
