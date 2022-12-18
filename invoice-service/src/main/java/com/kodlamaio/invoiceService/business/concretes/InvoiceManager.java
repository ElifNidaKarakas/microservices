package com.kodlamaio.invoiceService.business.concretes;

<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/business/concretes/InvoiceManager.java
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;
=======
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.InvoiceCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.request.CreateInvoiceRequest;
import com.kodlamaio.invoiceService.business.response.CreateInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;
import com.kodlamaio.invoiceService.kafka.InvoiceProducer;
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/business/concretes/InvoiceManager.java

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/business/concretes/InvoiceManager.java
	
	
	 @Override
	    public void add(Invoice invoice) {
	        invoice.setId(UUID.randomUUID().toString());
	        invoiceRepository.save(invoice);
	    }

	    @Override
	    public List<GetAllInvoiceResponse> getAll() {
	        List<Invoice> invoiceList = invoiceRepository.findAll();
	        List<GetAllInvoiceResponse> response = invoiceList.stream()
	                .map(invoice -> modelMapperService.forResponse().map(invoice,GetAllInvoiceResponse.class))
	                .collect(Collectors.toList());
	        return response;
	    }
	}
	


=======
	private InvoiceProducer invoiceProducer;

	public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {

		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());

		Invoice createInvoice = invoiceRepository.save(invoice);

		InvoiceCreatedEvent createdEvent = new InvoiceCreatedEvent();

		createdEvent.setPaymentId(createdEvent.getPaymentId());
		createdEvent.setMessage("Invoice created");

		this.invoiceProducer.sendMessage(createdEvent);
		
		CreateInvoiceResponse invoiceResponse=this.modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
		return invoiceResponse;
	}

}
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/business/concretes/InvoiceManager.java
