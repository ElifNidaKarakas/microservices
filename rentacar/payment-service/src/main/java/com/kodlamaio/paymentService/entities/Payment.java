package com.kodlamaio.paymentService.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payments")
public class Payment {
	@Id
    @Column(name = "id")
    private String id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_cvv")
    private String cardCvv;
    @Column(name = "balance")
    private double balance;
    @Column(name="statusPayment")
    private int statusPayment;
	 
		
}