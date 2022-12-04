package com.kodlamaio.invonteryServer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.CarRentalUpdateEvent;
import com.kodlamaio.invonteryServer.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdateConsumer {

	private CarService carService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdateConsumer.class);

	 @KafkaListener(
	            topics = "${spring.kafka.topic.name}"
	            , groupId = "rental-update")
    public void consume(CarRentalUpdateEvent event) {
    	 carService.changeState(3, event.getOldCarId());
	        carService.changeState(2, event.getNewCarId());
	        LOGGER.info("Car state updated!");
    	
    }
    
   
}
