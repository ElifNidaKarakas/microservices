package com.kodlamaio.filterServer.business.abstracts;

import java.util.List;

import com.kodlamaio.filterServer.business.response.GetAllCarFiltersResponse;
import com.kodlamaio.filterServer.business.response.GetFilterResponse;
import com.kodlamaio.filterServer.entities.CarFilter;

public interface CarFilterService {
 
   	
    //consumer service
    void delete(String id);
    void save(CarFilter mongodb) ;
  
    List<GetAllCarFiltersResponse> getAll();
    List<GetAllCarFiltersResponse> getByBrandName(String brandName);
    List<GetAllCarFiltersResponse> getByModelName(String modelName);
    
    CarFilter getByCarId(String id);
    List<CarFilter> getByModelId(String modelId);
    List<CarFilter> getByBrandId(String brandId);
    void deleteByCarId(String id);
    List<CarFilter> getByBrandNameOrModelName(String name);
    
}
