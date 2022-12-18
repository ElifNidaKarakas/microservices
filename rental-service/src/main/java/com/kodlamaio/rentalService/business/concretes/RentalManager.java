package com.kodlamaio.rentalService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.dto.GetCarResponse;
import com.kodlamaio.common.events.rental.CarRentalUpdateEvent;
import com.kodlamaio.common.events.rental.InvoiceCreatedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;
<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/business/concretes/RentalManager.java
import com.kodlamaio.rentalService.client.InventoryClient;
=======
import com.kodlamaio.rentalService.client.CarClient;
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/business/concretes/RentalManager.java
import com.kodlamaio.rentalService.client.PaymentClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
<<<<<<< HEAD:rentacar/rental-service/src/main/java/com/kodlamaio/rentalService/business/concretes/RentalManager.java
	private InventoryClient inventoryClient;
	private PaymentClient paymentClient;
=======
	private RentalUpdatedProducer rentalUpdatedProducer;
	private CarClient carClient;
	private PaymentClient paymentClient;
	
	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest) {
	carClient.checkIfCarAvailable(createRentalRequest.getCarId());
	Rental rental =this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	rental.setId(UUID.randomUUID().toString());
	rental.setTotalPrice(createRentalRequest.getDailyPrice()*createRentalRequest.getRentedForDays());
	

	 double price = rental.getDailyPrice()*rental.getRentedForDays();
	paymentClient.checkIfPaymentSuccessful(createPaymentRequest.getCardNumber(), createPaymentRequest.getFullName(),
			createPaymentRequest.getCardCvv(),price);
	
	
	rentalRepository.save(rental);
	RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
	rentalCreatedEvent.setCarId(rental.getCarId());
	rentalCreatedEvent.setMessage("Rental Created");
	
	this.rentalProducer.sendMessage(rentalCreatedEvent);
	
	CreateRentalResponse rentalResponse=this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return rentalResponse;
	
		// burda oluşturduğumuz kiralama işlemi messajı inventory e gönderiyoruz.. 
		//confuser->database ekledi, producer a bilgi gidiyor, 
		//oda consumer a gönderiyor, inventory e gönderilir. o araç kiralandığı bilgisi oraya gider
	}
>>>>>>> 85ae80248b127cbb1aa9e0a0224b008688395ea4:rental-service/src/main/java/com/kodlamaio/rentalService/business/concretes/RentalManager.java

	@Override
	public List<GetAllRentalResponse> getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		List<GetAllRentalResponse> response = rentals.stream()
				.map(rental -> modelMapperService.forResponse().map(rental, GetAllRentalResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public GetRentalResponse getById(String id) {
		checkIfRentalExistsById(id);
		Rental rental=rentalRepository.findById(id).get();
		GetRentalResponse response=this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		
		return null;
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest,
			CreatePaymentRequest createPaymentRequest) {

		checkIfCarAvialible(createRentalRequest.getCarId());

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays());

		paymentClient.checkIfPaymentSuccessful(createPaymentRequest.getCardNumber(), createPaymentRequest.getFullName(),
				createPaymentRequest.getCardCvv(), rental.getTotalPrice());
		rentalRepository.save(rental);

		Rental rentalCreated = rentalRepository.save(rental);

		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(rental.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");
		rentalProducer.sendMessage(rentalCreatedEvent);

		createInvoiceProducer(rental, createPaymentRequest);

		CreateRentalResponse rentalResponse = this.modelMapperService.forResponse().map(rental,
				CreateRentalResponse.class);
		return rentalResponse;

		// burda oluşturduğumuz kiralama işlemi messajı inventory e gönderiyoruz..
		// confuser->database ekledi, producer a bilgi gidiyor,
		// oda consumer a gönderiyor, inventory e gönderilir. o araç kiralandığı bilgisi
		// oraya gider
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest, String id) {
		Rental rentalrequest = checkIfRentalExistsById(id);
		checkIfCarAvialible(updateRentalRequest.getCarId());
		CarRentalUpdateEvent rentalUpdatedEvent = new CarRentalUpdateEvent();
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setId(id);
		rentalUpdatedEvent.setOldCarId(rentalrequest.getCarId());
		rentalRepository.save(rental);
		rentalUpdatedEvent.setNewCarId(rental.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");
		//rentalProducer.sendMessage(rentalUpdatedEvent);
		UpdateRentalResponse response = modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
		return response;
	}

	@Override
	public void delete(String id) {
		checkIfRentalExistsById(id);
		rentalRepository.deleteById(id);

	}

	private void createInvoiceProducer(Rental rental, CreatePaymentRequest paymentRequest) {
		InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
		GetCarResponse car = inventoryClient.getCarById(rental.getCarId());
		invoiceCreatedEvent.setBrandName(car.getBrandName());
		invoiceCreatedEvent.setModelName(car.getModelName());
		invoiceCreatedEvent.setTotalPrice(rental.getTotalPrice());
		invoiceCreatedEvent.setFullName(paymentRequest.getFullName());
		invoiceCreatedEvent.setDailyPrice(rental.getDailyPrice());
		invoiceCreatedEvent.setRentedForDays(rental.getRentedForDays());
		rentalProducer.sendMessage(invoiceCreatedEvent);

	}

	private void checkIfCarAvialible(String carId) {
		GetCarResponse carResponse = inventoryClient.getCarById(carId);
		if (carResponse.getState() != 1)
			throw new BusinessException("Car no avialible");
	}

	private Rental checkIfRentalExistsById(String id) {
		Rental rental = rentalRepository.findById(id).orElseThrow(() -> new BusinessException("Rental no exists"));
		return rental;
	}
}
