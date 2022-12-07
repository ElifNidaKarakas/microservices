package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;

public interface InvoiceService {
CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
}
