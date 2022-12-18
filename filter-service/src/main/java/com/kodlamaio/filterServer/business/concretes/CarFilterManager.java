package com.kodlamaio.filterServer.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterServer.business.abstracts.CarFilterService;
import com.kodlamaio.filterServer.business.response.GetAllCarFiltersResponse;
import com.kodlamaio.filterServer.business.response.GetFilterResponse;
import com.kodlamaio.filterServer.dataAccess.CarFilterRepository;
import com.kodlamaio.filterServer.entities.CarFilter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFilterManager implements CarFilterService {
	private CarFilterRepository carFilterRepository;
	private ModelMapperService modelMapperService;

	@Override
	public void delete(String id) {
		carFilterRepository.deleteByCarId(id);
	}

	@Override
	public List<GetAllCarFiltersResponse> getAll() {
		List<CarFilter> cars = carFilterRepository.findAll();
		List<GetAllCarFiltersResponse> response = cars.stream()
				.map(carFilter -> modelMapperService.forResponse().map(carFilter, GetAllCarFiltersResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public List<GetAllCarFiltersResponse> getByBrandName(String brandName) {
		List<CarFilter> filters = carFilterRepository.findByBrandNameContainingIgnoreCase(brandName);
		List<GetAllCarFiltersResponse> response = filters.stream()
				.map(filter -> modelMapperService.forResponse().map(filter, GetAllCarFiltersResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public List<GetAllCarFiltersResponse> getByModelName(String modelName) {
		List<CarFilter> filters = carFilterRepository.findByModelNameContainingIgnoreCase(modelName);
		List<GetAllCarFiltersResponse> response = filters.stream()
				.map(filter -> modelMapperService.forResponse().map(filter, GetAllCarFiltersResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void save(CarFilter mongodb) {
		carFilterRepository.save(mongodb);
	}

	@Override
	public CarFilter getByCarId(String id) {
		return carFilterRepository.findByCarId(id);
	}

	@Override
	public List<CarFilter> getByModelId(String modelId) {
		return carFilterRepository.findAllByModelId(modelId);
	}

	@Override
	public List<CarFilter> getByBrandId(String brandId) {
		return carFilterRepository.findAllByBrandId(brandId);
	}

	@Override
	public void deleteByCarId(String id) {
		carFilterRepository.deleteByCarId(id);
	}

	@Override
	public List<CarFilter> getByBrandNameOrModelName(String name) {
		List<CarFilter> filters = carFilterRepository.findAllByModelNameIgnoreCaseOrBrandNameIgnoreCase(name);
		return filters;
	}

}
