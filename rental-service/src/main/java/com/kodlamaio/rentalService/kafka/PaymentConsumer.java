package com.kodlamaio.rentalService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;

public class PaymentConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
	private RentalService rentalService;
	private ModelMapperService modelMapperService;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "create")
	public void consume(PaymentCreatedEvent event) {
		//this.rentalService.add(event.cre)
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
//		CreateRentalRequest request = this.modelMapperService.forRequest().map(event, CreateRentalRequest.class);
//		rentalService.add(request);
	}
}
