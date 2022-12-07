package com.kodlamaio.invoiceService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceResponse {
	private String id;
	private String paymentId;

	private String customerFirstName;

	private String customerLastName;

	private double tax;

	private double totalPrice;

	private String address;
}
