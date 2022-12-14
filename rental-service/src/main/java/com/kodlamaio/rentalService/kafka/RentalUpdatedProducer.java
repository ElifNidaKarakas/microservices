package com.kodlamaio.rentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.CarRentalUpdateEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdatedProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdatedProducer.class);

	private NewTopic topic;
	
	private KafkaTemplate<String, CarRentalUpdateEvent> kafkaTemplate;


	public void sendMessage(CarRentalUpdateEvent rentalUpdatedEvent) {
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));
		
		Message<CarRentalUpdateEvent> message = MessageBuilder
				.withPayload(rentalUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate.send(message);
	}
}