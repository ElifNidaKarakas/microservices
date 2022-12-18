package com.kodlamaio.filterServer.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterServer.entities.CarFilter;

import java.util.List;

public interface CarFilterRepository extends MongoRepository<CarFilter,String> {
	    List<CarFilter> findByBrandNameContainingIgnoreCase(String brandName);
	    List<CarFilter> findByModelNameContainingIgnoreCase(String brandName);
	    CarFilter findByCarId(String carId);
	    CarFilter findByModelId(String modelId);
	    List<CarFilter> findAllByModelId(String modelId);
	    List<CarFilter> findAllByBrandId(String modelId);
	    void deleteByCarId(String carId);
	    List<CarFilter> findAllByModelNameIgnoreCaseOrBrandNameIgnoreCase(String name);
}