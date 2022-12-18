package com.kodlamaio.invoiceService.webApiController;

<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
=======
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;

import lombok.AllArgsConstructor;

=======
import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;

import lombok.AllArgsConstructor;
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
@AllArgsConstructor
@RequestMapping("/api/invoices")
@RestController
public class InvoiceController {
	private InvoiceService invoiceService;

<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
	@GetMapping
	public List<GetAllInvoiceResponse> getAll() {
		return invoiceService.getAll();
	}
}
=======
	@PostMapping
	public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return invoiceService.add(createInvoiceRequest);
	}
}
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/webApiController/InvoiceController.java
