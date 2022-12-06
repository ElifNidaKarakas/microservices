package com.kodlamaio.rentalService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.CarRentalUpdateEvent;
import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalService.business.responses.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.UpdateRentalResponse;
import com.kodlamaio.rentalService.client.CarClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;
import com.kodlamaio.rentalService.kafka.RentalUpdatedProducer;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private RentalUpdatedProducer rentalUpdatedProducer;
	private CarClient carClient;
	
	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
	//carClient.checkIfCarAvailable(createRentalRequest.getCarId());
	Rental rental =this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	rental.setId(UUID.randomUUID().toString());
	rental.setTotalPrice(createRentalRequest.getDailyPrice()*createRentalRequest.getRentedForDays());
	rentalRepository.save(rental);
	
	RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
	rentalCreatedEvent.setCarId(rental.getCarId());
	rentalCreatedEvent.setMessage("Rental Created");
	
	this.rentalProducer.sendMessage(rentalCreatedEvent);
	
	CreateRentalResponse rentalResponse=this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);
		return rentalResponse;
		
	}

	@Override
	public List<GetAllRentalResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetRentalResponse getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
	     checkIfRentalExists(updateRentalRequest.getId());
	     Rental rental=this.rentalRepository.findById(updateRentalRequest.getId()).get();
		rental.setTotalPrice(updateRentalRequest.getDailyPrice()*updateRentalRequest.getRentedForDays());
		rentalRepository.save(rental);
		
		CarRentalUpdateEvent rentalUpdatedEvent = new CarRentalUpdateEvent();
		rentalUpdatedEvent.setOldCarId(rental.getCarId());
		Rental rentalSave=this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Rental rentalUpdate=this.rentalRepository.save(rentalSave);
		
		rentalUpdatedEvent.setNewCarId(updateRentalRequest.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");
	    rentalUpdatedProducer.sendMessage(rentalUpdatedEvent);
		
		UpdateRentalResponse rentalResponse=this.modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);
		return rentalResponse;
	}

	@Override
	public void delete(String id) {
		 checkIfRentalExists(id);
	        rentalRepository.deleteById(id);
		
	}
	
	 private void checkIfRentalExists(String id) {
	        if (!rentalRepository.existsById(id)) {
	            throw new BusinessException("Rental not found");
	        }
	    }

	@Override
	public double getTotalPrice(String id) {
		return rentalRepository.findById(id).get().getTotalPrice();
	}

}
