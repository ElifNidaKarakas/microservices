package com.kodlamaio.paymentService.business.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotBlank(message = "Card number is required")
    @Length(min = 16, max = 16, message = "Card number must be 16 characters long")
    private String cardNumber;
    @NotBlank(message = "Full name is required")
    @Length(min = 3, message = "Full name must be at least 3 characters long")
    private String fullName;
   
    @NotBlank(message = "Card CVV is required")
    @Length(min = 3, max = 3, message = "Card CVV must be 3 characters long")
    private String cardCvv;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private double price;
}
