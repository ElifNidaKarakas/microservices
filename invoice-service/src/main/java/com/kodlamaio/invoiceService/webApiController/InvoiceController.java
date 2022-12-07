package com.kodlamaio.invoiceService.webApiController;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RequestMapping("/api/invoices")
@RestController
public class InvoiceController {
	private InvoiceService invoiceService;

	@PostMapping
	public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return invoiceService.add(createInvoiceRequest);
	}
}
