package com.kodlamaio.paymentService.business.abstracts;

import java.util.List;

import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.request.UpdatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;
import com.kodlamaio.paymentService.business.responses.GetAllPaymentsResponse;

public interface PaymentService {
	CreatePaymentResponses add(CreatePaymentRequest request);

	void checkIfPaymentSuccessful(PaymentRequest request);

	List<GetAllPaymentsResponse> getAll();

	void update(String id, UpdatePaymentRequest updatePaymentRequest);
}
