package com.kodlamaio.invoiceService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD:rentacar/invoice-service/src/main/java/com/kodlamaio/invoiceService/entities/Invoice.java
import javax.persistence.Id;
import javax.persistence.Table;

=======
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:invoice-service/src/main/java/com/kodlamaio/invoiceService/entities/Invoice.java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invoices")
public class Invoice {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "paymentId")
	private String paymentId;
	
	@Column(name = "customerFirstName")
	private String customerFirstName;
	
	@Column(name = "customerLastName")
	private String customerLastName;

	@Column(name = "tax")
	private double tax;
	
	@Column(name = "totalPrice")
	private double totalPrice;
	
	@Column(name = "address")
	private String address;
}
