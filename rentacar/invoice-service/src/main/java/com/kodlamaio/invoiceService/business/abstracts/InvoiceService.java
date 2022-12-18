package com.kodlamaio.invoiceService.business.abstracts;

import java.util.List;

import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {
	 void add(Invoice invoice);
	    List<GetAllInvoiceResponse> getAll();
}
