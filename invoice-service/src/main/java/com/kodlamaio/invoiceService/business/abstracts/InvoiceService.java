package com.kodlamaio.invoiceService.business.abstracts;

<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/business/abstracts/InvoiceService.java
import java.util.List;

import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {
	 void add(Invoice invoice);
	    List<GetAllInvoiceResponse> getAll();
=======
import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;

public interface InvoiceService {
CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/business/abstracts/InvoiceService.java
}
