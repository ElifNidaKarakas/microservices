package com.kodlamaio.invonteryServer.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarResponse {
	private String id;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private int state;
	private String modelId;
}
