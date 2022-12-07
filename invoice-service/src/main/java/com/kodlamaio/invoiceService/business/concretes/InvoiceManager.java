package com.kodlamaio.invoiceService.business.concretes;

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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
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
