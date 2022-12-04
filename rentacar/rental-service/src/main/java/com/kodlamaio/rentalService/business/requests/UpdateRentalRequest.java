package com.kodlamaio.rentalService.business.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {
	@NotNull
	private String id;
	@NotNull
	private String carId;

	// private LocalDate dateStarted;
	@NotNull
	private int rentedForDays;
	@NotNull
	private double dailyPrice;
	@NotNull
	private double totalPrice;
}
