package com.kodlamaio.invonteryServer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.invonteryServer.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	CarService carService;

	@KafkaListener(topics = "rental", groupId = "${spring.kafka.consumer.group-id}")

	public void consume(RentalCreatedEvent event) {
		LOGGER.info(String.format("  Rental Created event received in stock service => %s", event.toString()));

		carService.changeCarState(event.getCarId(), 2);
		//araba eklendiğinde  yeni bir id ver ve state 2 yap.
		LOGGER.info("Car rented");
		
	}

}
