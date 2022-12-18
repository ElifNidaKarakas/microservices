package com.kodlamaio.invoiceService.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceRequest {

	private String paymentId;

	private String customerFirstName;

	private String customerLastName;

	private double tax;

	private double totalPrice;

	private String address;
}
