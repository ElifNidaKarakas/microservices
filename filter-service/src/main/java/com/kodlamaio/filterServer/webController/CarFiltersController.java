package com.kodlamaio.filterServer.webController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.filterServer.business.abstracts.CarFilterService;
import com.kodlamaio.filterServer.business.response.GetAllCarFiltersResponse;
import com.kodlamaio.filterServer.business.response.GetFilterResponse;
import com.kodlamaio.filterServer.entities.CarFilter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carFilters")
@AllArgsConstructor
public class CarFiltersController {

	private CarFilterService carFilterService;
	

    @GetMapping
    public List<GetAllCarFiltersResponse> getAll(){
        return carFilterService.getAll();
    }

    @GetMapping("/getByBrandName")
    public List<GetAllCarFiltersResponse> getByBrandName(@RequestParam String brandName){
        return carFilterService.getByBrandName(brandName);
    }

    @GetMapping("/getByModelName")
    public List<GetAllCarFiltersResponse> getByModelName(@RequestParam String modelName){
        return carFilterService.getByModelName(modelName);
    }

    @GetMapping("/getByCarId/{id}")
    public CarFilter getByCarId(@PathVariable String id) {
        return carFilterService.getByCarId(id);
    }

    @GetMapping("/getByModelId/{id}")
    public List<CarFilter> getByModelId(@PathVariable String modelId){
        return carFilterService.getByModelId(modelId);
    }

    @GetMapping("/getByBrandId/{id}")
    public List<CarFilter> getByBrandId(@PathVariable String brandId){
        return carFilterService.getByBrandId(brandId);
    }

	
	    
	}
