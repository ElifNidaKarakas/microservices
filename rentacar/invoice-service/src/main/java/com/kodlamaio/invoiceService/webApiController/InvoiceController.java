package com.kodlamaio.invoiceService.webApiController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/invoices")
@RestController
public class InvoiceController {
	private InvoiceService invoiceService;

	@GetMapping
	public List<GetAllInvoiceResponse> getAll() {
		return invoiceService.getAll();
	}
}