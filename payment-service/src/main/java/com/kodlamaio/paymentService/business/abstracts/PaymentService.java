package com.kodlamaio.paymentService.business.abstracts;

import com.kodlamaio.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.request.PaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponses;

public interface PaymentService {
	  //List<GetAllPaymentsResponse> getAll();
	    //GetPaymentResponse getById(String id);
	    CreatePaymentResponses add(CreatePaymentRequest request);
	    //UpdatePaymentResponse update(UpdatePaymentRequest request, String id);
	    void delete(String id);
	    void checkIfPaymentSuccessful(PaymentRequest request);
}
