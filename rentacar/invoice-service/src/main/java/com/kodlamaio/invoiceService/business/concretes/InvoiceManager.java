package com.kodlamaio.invoiceService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.response.GetAllInvoiceResponse;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	
	
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
	


