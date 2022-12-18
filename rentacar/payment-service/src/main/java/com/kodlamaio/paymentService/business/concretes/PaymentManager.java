package com.kodlamaio.paymentService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.abstracts.PosService;
import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;

import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.request.UpdatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;
import com.kodlamaio.paymentService.business.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentService.dataAccess.PaymentRepository;
import com.kodlamaio.paymentService.entities.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	
	    private final PaymentRepository paymentRepository;
	    private final ModelMapperService modelMapperService;
	    private final PosService posService;


	    @Override
	    public CreatePaymentResponses add(CreatePaymentRequest request) {
	        Payment payment = modelMapperService.forRequest().map(request, Payment.class);
	        payment.setId(UUID.randomUUID().toString());
	        paymentRepository.save(payment);
	        CreatePaymentResponses response = modelMapperService.forResponse().map(payment, CreatePaymentResponses.class);
	        return response;
	    }

	    @Override
	    public void checkIfPaymentSuccessful(PaymentRequest request) {
	        checkPayment(request);
	    }

	    @Override
	    public List<GetAllPaymentsResponse> getAll() {
	        List<Payment> payments = paymentRepository.findAll();
	        List<GetAllPaymentsResponse> response = payments.stream()
	                .map(payment -> modelMapperService
	                        .forResponse().map(payment, GetAllPaymentsResponse.class))
	                .collect(Collectors.toList());
	        return response;
	    }

	    @Override
	    public void update(String id, UpdatePaymentRequest request) {
	        Payment payment = paymentRepository.findById(id).orElseThrow(()->new BusinessException("Payment not found"));
	        payment.setBalance(request.getBalance());
	        payment.setId(id);
	        paymentRepository.save(payment);
	    }

	    private void checkPayment(PaymentRequest request) {
	        if (!paymentRepository.existsByCardNumberAndFullNameAndCardCvv(request.getCardNumber(), request.getFullName(), request.getCardCvv())) {
	            throw new BusinessException("Invalid payment");
	        }
	        double balance = paymentRepository.findByCardNumber(request.getCardNumber()).getBalance();
	        if (balance < request.getPrice()) {
	            throw new BusinessException("No enough money");
	        }
	        posService.pay();
	        Payment payment = paymentRepository.findByCardNumber(request.getCardNumber());
	        payment.setBalance(balance - request.getPrice());
	        paymentRepository.save(payment);
	    }
	}
