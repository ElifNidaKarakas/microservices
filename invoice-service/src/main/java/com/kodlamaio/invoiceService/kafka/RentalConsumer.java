package com.kodlamaio.invoiceService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.InvoiceCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
    private ModelMapperService modelMapperService;
    private InvoiceService invoiceService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "invoiceCreated"
    )
    public void consume(InvoiceCreatedEvent createdEvent){
        LOGGER.info(String.format("Order event received in invoice-service => %s", createdEvent.toString()));
        Invoice invoice = modelMapperService.forRequest().map(createdEvent,Invoice.class);
        invoiceService.add(invoice);
        LOGGER.info("invoice created");
        
    }
}
