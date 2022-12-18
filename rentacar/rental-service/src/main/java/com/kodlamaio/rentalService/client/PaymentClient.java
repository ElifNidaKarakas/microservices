package com.kodlamaio.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "paymentClient", url = "http://localhost:9011/")
public interface PaymentClient {
    @RequestMapping(method = RequestMethod.POST, value = "payment/api/payments/check")
    void checkIfPaymentSuccessful(
            @RequestParam String cardNumber,
            @RequestParam String fullName,
            @RequestParam String cardCvv,
            @RequestParam double price);
}
