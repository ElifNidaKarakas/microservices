package com.kodlamaio.rentalService.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteRentalResponse {
	private String id;

	private String carId;

	private LocalDate dateStarted;

	private int rentedForDays;

	private double dailyPrice;

	private double totalPrice;
}
