package com.kodlamaio.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import feign.Headers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.common.dto.GetCarResponse;

//senkron iletişim için olusturuyoruz.

@FeignClient(value = "inventoryservice",url = "http://localhost:9011")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET,value = "stock/api/cars/{carId}")
    @Headers(value = "Content-Type: application/json")
    GetCarResponse getCarById(@PathVariable String carId);
}