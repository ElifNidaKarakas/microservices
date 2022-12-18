package com.kodlamaio.paymentService.webController;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value = "RentalClient", url = "http://localhost:9011")
public interface RentalClientService {
	@RequestMapping(method= RequestMethod.GET ,value = "/rental/api/rentals/get/totalpricebyid/{rentalId}")
	@Headers(value="Content-Type: application/json")
	double getTotalPrice(@PathVariable String rentalId);

}
