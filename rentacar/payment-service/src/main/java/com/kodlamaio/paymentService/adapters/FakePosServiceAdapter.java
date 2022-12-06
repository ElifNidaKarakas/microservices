package com.kodlamaio.paymentService.adapters;

import java.util.Random;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.paymentService.business.abstracts.PosService;

public class FakePosServiceAdapter implements PosService {

	@Override
	public void pay() {
		int randomNumber = new Random().nextInt(2);
		if (randomNumber == 1) {
			throw new BusinessException("PAYMENT_FAILED");
		}
	}
}