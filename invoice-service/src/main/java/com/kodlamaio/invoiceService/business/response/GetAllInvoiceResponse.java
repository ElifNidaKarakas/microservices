package com.kodlamaio.invoiceService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllInvoiceResponse {
	 private String id;
	    private String fullName;
	    private String brandName;
	    private String modelName;
	    private double dailyPrice;
	    private int rentedForDays;
	    private double totalPrice;

}
