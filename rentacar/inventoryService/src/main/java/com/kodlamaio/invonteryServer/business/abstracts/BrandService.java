package com.kodlamaio.invonteryServer.business.abstracts;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.kodlamaio.invonteryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateBrandResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetBrandResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateBrandResponse;

public interface BrandService {
List<GetAllBrandsResponse> getAll();
CreateBrandResponse add(CreateBrandRequest createBrandRequest);
UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
void delete(String id);
GetBrandResponse getById(String id);
}
