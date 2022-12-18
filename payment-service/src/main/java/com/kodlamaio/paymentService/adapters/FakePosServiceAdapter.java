package com.kodlamaio.paymentService.adapters;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.paymentService.business.abstracts.PosService;

@Service
public class FakePosServiceAdapter implements PosService {
// gercek bankamız olmadıgı için random değer  veren bir pos servis olusturyoruz
	@Override
	public void pay() {
		int randomNumber = new Random().nextInt(10);
		if (randomNumber <5) {
			throw new BusinessException("PAYMENT_FAILED");
		}
	}
}