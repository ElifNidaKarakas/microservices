package com.kodlamaio.invonteryServer.webApiControllor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invonteryServer.business.abstracts.CarService;
import com.kodlamaio.invonteryServer.business.request.create.CreateCarRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateCarRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateCarResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetCarResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/cars")
@RestController
public class CarController {
	private CarService carService;

	@GetMapping
	public List<GetAllCarsResponse> getAll() {
		return carService.getAll();
	}


	@PostMapping
	public CreateCarResponse add(@RequestBody CreateCarRequest createCarRequest) {
		return carService.add(createCarRequest);
	}

	@PutMapping("/{id}")
	public UpdateCarResponse update(@RequestBody UpdateCarRequest updateCarRequest, @PathVariable String id) {
		return carService.update(updateCarRequest, id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		carService.delete(id);
	}

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable  String id) {
        return carService.getById(id);
    }
    
	@GetMapping("/carAvialibleState/{carId}")
	public void carAvialibleState(@PathVariable String carId) {
		carService.carAvialibleState(carId);
	}
}
