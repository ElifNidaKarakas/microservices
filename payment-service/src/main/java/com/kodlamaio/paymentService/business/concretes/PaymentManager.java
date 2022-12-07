package com.kodlamaio.paymentService.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.abstracts.PosService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;
import com.kodlamaio.paymentService.dataAccess.PaymentRepository;
import com.kodlamaio.paymentService.entities.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	private PaymentRepository repository;
	private ModelMapperService modelMapperService;
	private PosService posService;
	

	@Override
	public CreatePaymentResponses add(CreatePaymentRequest createPaymentRequest) {
		checkIfCardNumberExists(createPaymentRequest.getCardNumber());
		Payment payment=this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		repository.save(payment);
		CreatePaymentResponses response=this.modelMapperService.forResponse().map(payment, CreatePaymentResponses.class);
		return response;

	}

	
	@Override
	public void delete(String id) {
		checkIfPaymentExists(id);
		repository.deleteById(id);
		
	}

	@Override
	public void checkIfPaymentSuccessful(PaymentRequest request) {
		checkPayment(request);
		
	}
	
	 private void checkPayment(PaymentRequest request) {
	        if (!repository.existsByCardNumberAndFullNameAndCardCvv(
	                request.getCardNumber(),
	                request.getFullName(),
	                request.getCardCvv())) {
	            throw new BusinessException("NOT_A_VALID_PAYMENT!");
	        } else {
	            double balance = repository.findByCardNumber(request.getCardNumber()).getBalance();
	            if (balance < request.getPrice()) {
	                throw new BusinessException("NOT_ENOUGH_MONEY!");
	            } 
	            
	            posService.pay();
	            Payment payment=repository.findByCardNumber(request.getCardNumber());
	            payment.setBalance(balance-request.getPrice());
	            repository.save(payment);
	        }
	    }

	    private void checkIfPaymentExists(String id) {
	        if (!repository.existsById(id)) {
	            throw new BusinessException("PAYMENT_NOT_FOUND!");
	        }
	    }

	    private void checkIfCardNumberExists(String cardNumber) {
	        if (repository.existsByCardNumber(cardNumber)) {
	            throw new BusinessException("CARD_NUMBER_ALREADY_EXISTS!");
	        }
	    }
}
