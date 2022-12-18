package com.kodlamaio.invonteryServer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.CarRentalUpdateEvent;
import com.kodlamaio.invonteryServer.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdateConsumer {

	private CarService carService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdateConsumer.class);

	 @KafkaListener(
	            topics = "rental-update"
	            , groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CarRentalUpdateEvent event) {
		 LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		 carService.changeCarState( event.getOldCarId(),2);
         carService.changeCarState( event.getNewCarId(),1);
         
         //araba kiralandığında state durumlarını değiştir. Kiralık olup olmadığı belli olsun diye 
	        LOGGER.info("Car state updated!");
    	
    }
    
   
}
