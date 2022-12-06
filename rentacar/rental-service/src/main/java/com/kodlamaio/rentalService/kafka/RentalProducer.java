package com.kodlamaio.rentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentReceivedEvent;
import com.kodlamaio.common.events.RentalCreatedEvent;
//msj bilgileri bu classda veririz
@Service
public class RentalProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);

	private NewTopic topic;
	
	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate;

	public RentalProducer(NewTopic topic, KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));
		
		Message<RentalCreatedEvent> message = MessageBuilder.withPayload(rentalCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate.send(message);
	}
	
	 public void sendMessage(PaymentReceivedEvent event) {
	        LOGGER.info(String.format("Payment received event => %s", event.toString()));

	        Message<PaymentReceivedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

	        kafkaTemplate.send(message);
	    }
	
	
}